package com.belenot.mirea.schedule.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.Subject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubjectDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Subject getSubject(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(Subject.class).load(id);
    }

    @Transactional
    public Subject addSubject(Subject subject) {
	Session session = sessionFactory.getCurrentSession();
	session.save(subject);
        return subject;
    }

    @Transactional
    public void deleteSubject(Subject subject) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(subject);
    }
    
    @Transactional
    public Subject getByTitle(String title) {
	Session session = sessionFactory.getCurrentSession();
	return session.bySimpleNaturalId(Subject.class).load(title);
    }

    @Transactional
    public List<Subject> getSubjects() {
	Session session = sessionFactory.getCurrentSession();
	return session.createQuery("select s from Subject s", Subject.class).list();
    }
}
