package com.belenot.mirea.schedule.service;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.belenot.mirea.schedule.ScheduleModel;
import com.belenot.mirea.schedule.ScheduleParser;
import com.belenot.mirea.schedule.ScheduledSubjectModel;
import com.belenot.mirea.schedule.dao.ScheduleDao;
import com.belenot.mirea.schedule.domain.Classroom;
import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.ScheduledSubject;
import com.belenot.mirea.schedule.domain.ScheduledSubject.LessonTime;
import com.belenot.mirea.schedule.domain.ScheduledSubject.LessonType;
import com.belenot.mirea.schedule.domain.Subject;
import com.belenot.mirea.schedule.domain.Teacher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements Closeable {

    private Logger logger = LogManager.getLogger(this);
    
    @Autowired
    private ScheduleDao scheduleDao;
    
    @Autowired
    private Environment env;
    @Autowired
    private ScheduleParser parser;

    public List<String> getGroupNames() {
	return parser.getGroupNames();
    }
    //Temporary
    public Map<String, Boolean> saveAllSchedules() {
	Map<String, Boolean> saveResults = new HashMap<>();
	for (String groupName : getGroupNames()) {
	    try {
		groupName = groupName.substring(0, 10);
		Schedule schedule = parseSchedule(groupName);
		scheduleDao.addSchedule(schedule);
	    } catch (Exception exc) {
		saveResults.put(groupName, false);
		logger.error("GroupName: " + groupName, exc);
		continue;
	    }
	    saveResults.put(groupName, true);
	}
	return saveResults;
    }

    
    //Refactor!
    public Schedule getSchedule(String groupName) {
	Schedule schedule = scheduleDao.getByGroupName(groupName);
	if (schedule == null) {
	    schedule = parseSchedule(groupName);
	    if (schedule == null) return null;
	    scheduleDao.addSchedule(schedule);
	} 
	schedule = scheduleDao.getFullLoaded(schedule.getId());
	return schedule;
    }
    
    public void close() {
	parser.close();
    }

    protected Schedule parseSchedule(String groupName) {
	Schedule schedule = new Schedule();
	List<ScheduledSubject> scheduledSubjects = new ArrayList<>(400);
	schedule.setGroupName(groupName);
	ScheduleModel model = parser.parseSchedule(groupName);
	for (ScheduledSubjectModel subjectModel : model.getScheduledSubjectsModels()) {
	    scheduledSubjects.add(convertSubject(subjectModel));
	}
	schedule.setScheduledSubjects(scheduledSubjects);
	return schedule;
    }
       
    protected ScheduledSubject convertSubject(ScheduledSubjectModel model) {
	ScheduledSubject scheduledSubject = new ScheduledSubject();
	scheduledSubject.setSubject(new Subject().setTitle(model.getSubjectTitle()));
	scheduledSubject.setDate(model.getDate());
	scheduledSubject.setLessonTime(LessonTime.byNumber(model.getLessonNumber().get()));
	
	if (model.getTeacherShortName().isPresent()) {
	    scheduledSubject.setTeacher(new Teacher().setShortName(model.getTeacherShortName().get()));
	}

	if (model.getClassroomNumber().isPresent()) {
	    scheduledSubject.setClassroom(new Classroom().setNumber(model.getClassroomNumber().get()));
	}

	if (model.getLessonType().isPresent()) {
	    scheduledSubject.setLessonType(LessonType.byString(model.getLessonType().get()));
	}
	return scheduledSubject;
    }
    
    
    
}
