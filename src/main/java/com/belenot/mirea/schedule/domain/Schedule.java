package com.belenot.mirea.schedule.domain;

import java.util.List;

public class Schedule {
    private StudentGroup studentGroup;
    private List<ScheduledSubject> scheduledSubjects;
    public StudentGroup getStudentGroup() {
	return studentGroup;
    }
    public void setStudentGroup(StudentGroup studentGroup) {
	this.studentGroup = studentGroup;
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public void setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
    }
}
