package com.belenot.mirea.schedule.support;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.belenot.mirea.schedule.domain.ScheduledSubject.LessonTime;
import com.belenot.mirea.schedule.domain.ScheduledSubject.LessonType;

public class ScheduledSubjectFilter {
    
    private List<FilteringValue<Integer>> teachersIds = new ArrayList<>();
    private List<FilteringValue<Integer>> classroomsIds = new ArrayList<>();
    private List<FilteringValue<Integer>> subjectsIds = new ArrayList<>();
    private List<FilteringValue<Integer>> schedulesIds = new ArrayList<>();
    private List<FilteringValue<LessonTime>> lessonTimes = new ArrayList<>();
    private List<FilteringValue<LessonType>> lessonTypes = new ArrayList<>();
    private List<FilteringValue<Integer>> studentGroupsIds = new ArrayList<>();
    private List<FilteringValue<IntervalValue<Date>>> dateIntervals = new ArrayList<>();

    
    public void setTeachersIds(List<FilteringValue<Integer>> teachersIds) {
	this.teachersIds = teachersIds;
    }
    public void setClassroomsIds(List<FilteringValue<Integer>> classroomsIds) {
	this.classroomsIds = classroomsIds;
    }
    public void setSubjectsIds(List<FilteringValue<Integer>> subjectsIds) {
	this.subjectsIds = subjectsIds;
    }
    public void setSchedulesIds(List<FilteringValue<Integer>> schedulesIds) {
	this.schedulesIds = schedulesIds;
    }
    public List<FilteringValue<LessonTime>> getLessonTimes() {
	return lessonTimes;
    }
    public void setLessonTimes(List<FilteringValue<LessonTime>> lessonTimes) {
	this.lessonTimes = lessonTimes;
    }
    public List<FilteringValue<LessonType>> getLessonTypes() {
	return lessonTypes;
    }
    public void setLessonTypes(List<FilteringValue<LessonType>> lessonTypes) {
	this.lessonTypes = lessonTypes;
    }
    public List<FilteringValue<Integer>> getTeachersIds() {
	return teachersIds;
    }
    public List<FilteringValue<Integer>> getClassroomsIds() {
	return classroomsIds;
    }
    public List<FilteringValue<Integer>> getSubjectsIds() {
	return subjectsIds;
    }
    public List<FilteringValue<Integer>> getSchedulesIds() {
	return schedulesIds;
    }
    public List<FilteringValue<Integer>> getStudentGroupsIds() {
	return studentGroupsIds;
    }
    public void setStudentGroupsIds(List<FilteringValue<Integer>> studentGroupsIds) {
	this.studentGroupsIds = studentGroupsIds;
    }
    public List<FilteringValue<IntervalValue<Date>>> getDateIntervals() {
	return dateIntervals;
    }
    public void setDateIntervals(List<FilteringValue<IntervalValue<Date>>> dateIntervals) {
	this.dateIntervals = dateIntervals;
    }

    
    
}
