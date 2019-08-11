package com.belenot.mirea.schedule.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Classroom;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClassroomDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Classroom getClassroom(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(Classroom.class).load(id);
    }

    @Transactional
    public Classroom addClassroom(Classroom teacher) {
	Session session = sessionFactory.getCurrentSession();
	session.save(teacher);
        return teacher;
    }

    @Transactional
    public void deleteClassroom(Classroom teacher) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(teacher);
    }

    @Transactional
    public Classroom getByNumber(String number) {
	Session session = sessionFactory.getCurrentSession();
	return session.byNaturalId(Classroom.class).using("number", number).load();
    }
    
}
