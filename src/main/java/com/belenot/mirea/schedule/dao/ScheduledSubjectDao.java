package com.belenot.mirea.schedule.dao;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Classroom;
import com.belenot.mirea.schedule.domain.ScheduledSubject;
import com.belenot.mirea.schedule.domain.Subject;
import com.belenot.mirea.schedule.domain.Teacher;

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

}
