package com.belenot.mirea.schedule.service;

import java.util.List;

import com.belenot.mirea.schedule.dao.ClassroomDao;
import com.belenot.mirea.schedule.domain.Classroom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    @Autowired
    private ClassroomDao classroomDao;

    public List<Classroom> getClassrooms() {
	return classroomDao.getClassrooms();
    }

    public Classroom getClassroom(int id) {
	return classroomDao.getClassroom(id);
    }
}
