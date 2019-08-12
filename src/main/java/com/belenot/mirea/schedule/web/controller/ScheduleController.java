package com.belenot.mirea.schedule.web.controller;

import java.util.List;
import java.util.Map;

import com.belenot.mirea.schedule.dao.ScheduledSubjectDao;
import com.belenot.mirea.schedule.domain.Classroom;
import com.belenot.mirea.schedule.domain.Schedule;
import com.belenot.mirea.schedule.domain.ScheduledSubject;
import com.belenot.mirea.schedule.domain.Subject;
import com.belenot.mirea.schedule.domain.Teacher;
import com.belenot.mirea.schedule.service.ClassroomService;
import com.belenot.mirea.schedule.service.ScheduleService;
import com.belenot.mirea.schedule.service.SubjectService;
import com.belenot.mirea.schedule.service.TeacherService;
import com.belenot.mirea.schedule.support.ScheduledSubjectFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/api" )
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private ScheduledSubjectDao scheduledSubjectDao;
    
    @ResponseBody
    @GetMapping( path = "/schedule/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Schedule getSchedule(@PathVariable int id) {
	return scheduleService.getSchedule(id);
    }

    @ResponseBody
    @GetMapping( path = "/schedule", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Map<Integer, String> getSchedulesGroups() {
	return scheduleService.getSchedulesGroups();
    }

    @ResponseBody
    @GetMapping( path = "/update/status",  produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, Boolean>  init() {
	return scheduleService.getSchedulesUpdateStatus();
    }

    @ResponseBody
    @GetMapping( path = "/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public List<Teacher> teachers() {
	return teacherService.getTeachers();
    }

    @ResponseBody
    @GetMapping( path = "/teacher/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Teacher teacher(@PathVariable( "id" ) int id) {
	return teacherService.getTeacher(id);
    }
    

    @ResponseBody
    @GetMapping( path = "/subject", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public List<Subject> subjects() {
	return subjectService.getSubjects();
    }

    @ResponseBody
    @GetMapping( path = "/subject/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Subject subject(@PathVariable( "id" ) int id) {
	return subjectService.getSubject(id);
    }

    @ResponseBody
    @GetMapping( path = "/classroom", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public List<Classroom> classrooms() {
	return classroomService.getClassrooms();
    }

    @ResponseBody
    @GetMapping( path = "/classroom/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE )
    public Classroom classroom(@PathVariable( "id" ) int id) {
	return classroomService.getClassroom(id);
    }

    @ResponseBody
    @PostMapping( path = "/schedule/filter", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ScheduledSubject> getFilteredScheduledSubjects(@RequestBody ScheduledSubjectFilter filter) {
	return scheduledSubjectDao.getByFilter(filter);
    }
    
   
}
