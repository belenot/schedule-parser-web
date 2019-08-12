package com.belenot.mirea.schedule.service;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService implements Closeable, ApplicationListener<ContextRefreshedEvent> {

    private Logger logger = LogManager.getLogger(this);
    private Date schedulesUpdatedDate;
    private Date updateSchedulesDate;
    private int schedulesUpdateDateInterval;
    private boolean initialized = false;
    private Map<String, Boolean> schedulesUpdateStatus = new HashMap<>();
    
    @Autowired
    private ScheduleDao scheduleDao;
    
    @Autowired
    private Environment env;
    @Autowired
    private ScheduleParser parser;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
	if (schedulesUpdatedDate == null || updateSchedulesDate.before(new Date())) {
	    schedulesUpdatedDate = new GregorianCalendar().getTime();
	    Calendar calendar = new GregorianCalendar();
	    calendar.add(GregorianCalendar.DATE, schedulesUpdateDateInterval);
	    updateSchedulesDate = calendar.getTime();
	    schedulesUpdateStatus = saveAllSchedules();
	}
	
    }

    public boolean isInitialized() {
	return initialized;
    }

    public Map<String, Boolean> getSchedulesUpdateStatus() {
	return schedulesUpdateStatus;
    }

    public Map<Integer, String> getSchedulesGroups() {
	return scheduleDao.getSchedulesGroups();
    }
    
    public Map<String, Boolean> saveAllSchedules() {
	Map<String, Boolean> saveResults = new HashMap<>();
	for (String groupName : parser.getGroupNames()) {
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

    public Schedule getSchedule(int id) {
        return scheduleDao.getFullLoaded(id);
    }
    
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
