package com.belenot.mirea.schedule.service;

import java.util.List;

import com.belenot.mirea.schedule.dao.SubjectDao;
import com.belenot.mirea.schedule.domain.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectService {
    @Autowired
    private SubjectDao subjectDao;

    public List<Subject> getSubjects() {
	return subjectDao.getSubjects();
    }

    public Subject getSubject(int id) {
	return subjectDao.getSubject(id);
    }
}
