package com.belenot.mirea.schedule.dao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Classroom;
import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.ScheduledSubject;
import com.belenot.mirea.schedule.domain.StudentGroup;
import com.belenot.mirea.schedule.domain.Subject;
import com.belenot.mirea.schedule.domain.Teacher;
import com.belenot.mirea.schedule.support.FilteringValue;
import com.belenot.mirea.schedule.support.IntervalValue;
import com.belenot.mirea.schedule.support.ScheduledSubjectFilter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduledSubjectDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public ScheduledSubject getScheduledSubject(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(ScheduledSubject.class).load(id);
    }

    @Transactional
    public ScheduledSubject addScheduledSubject(ScheduledSubject scheduledSubject) {
	Session session = sessionFactory.getCurrentSession();
	session.save(scheduledSubject);
        return scheduledSubject;
    }

    @Transactional
    public Schedule addSchedule(Schedule schedule) {
	Session session = sessionFactory.getCurrentSession();
	int batchSize = 25;
	int savedIteration = 0;
	StudentGroup studentGroup = schedule.getStudentGroup();
	session.save(studentGroup);
	session.flush();
	for (ScheduledSubject scheduledSubject : schedule.getScheduledSubjects()) {
	    ScheduledSubject scheduledSubjectSchrodinger = getByNaturalId(scheduledSubject);
	    if (scheduledSubjectSchrodinger != null) {
	        scheduledSubjectSchrodinger.addStudentGroup(studentGroup);
	    } else {
		if (scheduledSubject.getClassroom() != null) {
		    Classroom classroom = session.byNaturalId(Classroom.class)
			.using("number", scheduledSubject.getClassroom().getNumber())
			.using("location", scheduledSubject.getClassroom().getLocation()).load();
		    if (classroom == null) {
			session.save(scheduledSubject.getClassroom());
			classroom = scheduledSubject.getClassroom();
		    }
		    classroom.addScheduledSubject(scheduledSubject);
		}
		if (scheduledSubject.getSubject() != null) {
		    Subject subject = session.bySimpleNaturalId(Subject.class).load(scheduledSubject.getSubject().getTitle());
		    if (subject == null) {
			session.save(scheduledSubject.getSubject());
			subject = scheduledSubject.getSubject();
		    }
		    subject.addScheduledSubject(scheduledSubject);
		}
		if (scheduledSubject.getTeacher() != null) {
		    Teacher teacher = session.bySimpleNaturalId(Teacher.class).load(scheduledSubject.getTeacher().getShortName());
		    if (teacher == null) {
			session.save(scheduledSubject.getTeacher());
			teacher = scheduledSubject.getTeacher();
		    }
		    teacher.addScheduledSubject(scheduledSubject);
		}
		scheduledSubject.addStudentGroup(studentGroup);
		session.save(scheduledSubject);
	    }
	    savedIteration++;
	    if (savedIteration == batchSize) {
		session.flush();
		session.clear();
		savedIteration = 0;
	    } 
	}
        return schedule;
    }

    @Transactional
    public void deleteScheduledSubject(ScheduledSubject scheduledSubject) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(scheduledSubject);
    }

    @Transactional
    public ScheduledSubject getByNaturalId(ScheduledSubject scheduledSubject) {
	Session session = sessionFactory.getCurrentSession();
	boolean subjectGiven = scheduledSubject.getSubject() != null;
	boolean teacherGiven = scheduledSubject.getTeacher() != null;
	boolean classroomGiven = scheduledSubject.getClassroom() != null;
	Subject subject = subjectGiven ?
	    session.bySimpleNaturalId(Subject.class)
	    .load(scheduledSubject.getSubject().getTitle()) : null;
	Teacher teacher = teacherGiven ?
	    session.bySimpleNaturalId(Teacher.class)
	    .load(scheduledSubject.getTeacher().getShortName()) : null;
	Classroom classroom = classroomGiven ?
	    session.byNaturalId(Classroom.class)
	    .using("location", scheduledSubject.getClassroom().getLocation())
	    .using("number", scheduledSubject.getClassroom().getNumber())
	    .load() : null;

	return session.byNaturalId(ScheduledSubject.class)
	    .using("subject", subject).using("date", scheduledSubject.getDate())
	    .using("classroom", classroom).using("teacher", teacher)
	    .using("lessonType", scheduledSubject.getLessonType())
	    .using("lessonTime", scheduledSubject.getLessonTime()).load();
    }

    @Transactional
    public List<ScheduledSubject> getByFilter(ScheduledSubjectFilter filter) {
	Session session = sessionFactory.getCurrentSession();
	if (filter.getTeachersIds().stream().anyMatch( v -> !v.isExcluded()))
	    session.enableFilter("teacherFilter").setParameterList("teachersIds", filter.getTeachersIds().stream().filter( v -> !v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getSubjectsIds().stream().anyMatch( v -> !v.isExcluded()))
	    session.enableFilter("subjectFilter").setParameterList("subjectsIds",  filter.getSubjectsIds().stream().filter( v -> !v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getClassroomsIds().stream().anyMatch( v -> !v.isExcluded()))
	    session.enableFilter("classroomFilter").setParameterList("classroomsIds", filter.getClassroomsIds().stream().filter( v -> !v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getLessonTimes().stream().anyMatch( v -> !v.isExcluded()))
	    session.enableFilter("lessonTimeFilter").setParameterList("lessonTimes", filter.getLessonTimes().stream().filter( v -> !v.isExcluded()).map( v -> v.getValue().name()).collect(Collectors.toList()));
	if (filter.getLessonTypes().stream().anyMatch( v -> !v.isExcluded()))
	    session.enableFilter("lessonTypeFilter").setParameterList("lessonTypes", filter.getLessonTypes().stream().filter( v -> !v.isExcluded()).map( v -> v.getValue().name()).collect(Collectors.toList()));
	
	if (filter.getTeachersIds().stream().anyMatch( v -> v.isExcluded()))
	    session.enableFilter("notTeacherFilter").setParameterList("teachersIds", filter.getTeachersIds().stream().filter( v -> v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getSubjectsIds().stream().anyMatch( v -> v.isExcluded()))
	    session.enableFilter("notSubjectFilter").setParameterList("subjectsIds",  filter.getSubjectsIds().stream().filter( v -> v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getClassroomsIds().stream().anyMatch( v -> v.isExcluded()))
	    session.enableFilter("notClassroomFilter").setParameterList("classroomsIds", filter.getClassroomsIds().stream().filter( v -> v.isExcluded()).map( v -> v.getValue()).collect(Collectors.toList()));
	if (filter.getLessonTimes().stream().anyMatch( v -> v.isExcluded()))
	    session.enableFilter("notLessonTimeFilter").setParameterList("lessonTimes", filter.getLessonTimes().stream().filter( v -> v.isExcluded()).map( v -> v.getValue().name()).collect(Collectors.toList()));
	if (filter.getLessonTypes().stream().anyMatch( v -> v.isExcluded()))
	    session.enableFilter("notLessonTypeFilter").setParameterList("lessonTypes", filter.getLessonTypes().stream().filter( v -> v.isExcluded()).map( v -> v.getValue().name()).collect(Collectors.toList()));
	
	if (filter.getDateIntervals().size() > 0)
	    for (FilteringValue<IntervalValue<Date>> filteringDateInterval : filter.getDateIntervals()) {
		IntervalValue<Date> dateInterval = filteringDateInterval.getValue();
		if (!filteringDateInterval.isExcluded()) {
		    session.enableFilter("dateFilter").setParameter("dateFirst", dateInterval.getMinValue()).setParameter("dateLast", dateInterval.getMaxValue());
		} else {
		    session.enableFilter("notDateFilter").setParameter("dateFirst", dateInterval.getMinValue()).setParameter("dateLast", dateInterval.getMaxValue());
		}
	    }
	if (filter.getStudentGroupsIds().size() > 0)
	    return session.createNamedQuery("ScheduledSubject_byStudentGroup", ScheduledSubject.class).setParameterList("studentGroupsIds", filter.getStudentGroupsIds().stream().map( v -> v.getValue()).collect(Collectors.toList())).list();
	return session.createQuery("select ss from ScheduledSubject ss", ScheduledSubject.class).list();
    }

}
