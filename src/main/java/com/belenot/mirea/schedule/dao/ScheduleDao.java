package com.belenot.mirea.schedule.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Classroom;
import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.ScheduledSubject;
import com.belenot.mirea.schedule.domain.Subject;
import com.belenot.mirea.schedule.domain.Teacher;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private ClassroomDao classroomDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ScheduledSubjectDao scheduledSubjectDao;

    @Transactional
    public Schedule getSchedule(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(Schedule.class).load(id);
    }

    @Transactional
    public Schedule addSchedule(Schedule schedule) {
	Session session = sessionFactory.getCurrentSession();
	int batchSize = 25;
	int savedIteration = 0;
	List<ScheduledSubject> scheduledSubjects = new ArrayList<>();
	scheduledSubjects.addAll(schedule.getScheduledSubjects());
	schedule.getScheduledSubjects().clear();
	session.save(schedule);
	session.flush();
	for (ScheduledSubject scheduledSubject : scheduledSubjects) {
	    ScheduledSubject scheduledSubjectSchrodinger = scheduledSubjectDao.getByNaturalId(scheduledSubject);
	    if (scheduledSubjectSchrodinger != null) {
	        scheduledSubjectSchrodinger.addSchedule(schedule);
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
		scheduledSubject.addSchedule(schedule);
		session.save(scheduledSubject);
	    }
	    savedIteration++;
	    if (savedIteration == batchSize) {
		session.flush();
		session.clear();
		savedIteration = 0;
	    }
	}
	//session.save(schedule);
        return schedule;
    }

    @Transactional
    public void deleteSchedule(Schedule schedule) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(schedule);
    }

    @Transactional
    public Schedule getByGroupName(String groupName) {
	Session session = sessionFactory.getCurrentSession();
	List<Schedule> schedules = session.createQuery("select s from Schedule s where groupName=:groupName", Schedule.class).setParameter("groupName", groupName).list();
	return schedules.size() > 0 ? schedules.get(0) : null;
    }

    @Transactional
    public Schedule getFullLoaded(int id) {
	Session session = sessionFactory.getCurrentSession();
	Schedule schedule = session.byId(Schedule.class).with(CacheMode.IGNORE).load(id);
	schedule.setScheduledSubjects(session.byMultipleIds(ScheduledSubject.class).with(CacheMode.IGNORE).multiLoad(schedule.getScheduledSubjects().stream().map( ss -> ss.getId()).collect(Collectors.toList())));
	return schedule;
    }
	
    
}
