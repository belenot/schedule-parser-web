package com.belenot.mirea.schedule.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.belenot.mirea.schedule.domain.StudentGroup;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentGroupDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public StudentGroup getStudentGroup(int id) {
	Session session = sessionFactory.getCurrentSession();
        return session.byId(StudentGroup.class).load(id);
    }

    @Transactional
    public void deleteStudentGroup(StudentGroup studentGroup) {
	Session session = sessionFactory.getCurrentSession();
	session.delete(studentGroup);
    }

    @Transactional
    public StudentGroup getByGroupName(String groupName) {
	Session session = sessionFactory.getCurrentSession();
	return session.bySimpleNaturalId(StudentGroup.class).load(groupName);
    }

    @Transactional
    public List<StudentGroup> getStudentGroups() {
	Session session = sessionFactory.getCurrentSession();
	List<StudentGroup> studentGroups = session.createQuery("select sg from StudentGroup sg", StudentGroup.class).list();
	return studentGroups;
    }
	
    
}
