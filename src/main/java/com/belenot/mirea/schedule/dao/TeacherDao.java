package com.belenot.mirea.schedule.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.Teacher;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public Teacher getTeacher(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(Teacher.class).load(id);
    }

    @Transactional
    public Teacher addTeacher(Teacher teacher) {
	Session session = sessionFactory.getCurrentSession();
	session.save(teacher);
        return teacher;
    }

    @Transactional
    public void deleteTeacher(Teacher teacher) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(teacher);
    }
    
    @Transactional
    public Teacher getByShortName(String shortName) {
	Session session = sessionFactory.getCurrentSession();
	return session.bySimpleNaturalId(Teacher.class).load(shortName);
    }
}
