package com.belenot.mirea.schedule.service;

import java.util.List;

import com.belenot.mirea.schedule.dao.TeacherDao;
import com.belenot.mirea.schedule.domain.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    public List<Teacher> getTeachers() {
	return teacherDao.getTeachers();
    }

    public Teacher getTeacher(int id) {
	return teacherDao.getTeacher(id);
    }
}
