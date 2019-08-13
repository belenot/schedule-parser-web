package com.belenot.mirea.schedule.service;

import java.util.List;

import com.belenot.mirea.schedule.dao.StudentGroupDao;
import com.belenot.mirea.schedule.domain.StudentGroup;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentGroupService {

    @Autowired
    private StudentGroupDao studentGroupDao;

    public StudentGroup getStudentGroup(int id) {
        return studentGroupDao.getStudentGroup(id);
    }

    public List<StudentGroup> getStudentGroups() {
	return studentGroupDao.getStudentGroups();
    }

}
