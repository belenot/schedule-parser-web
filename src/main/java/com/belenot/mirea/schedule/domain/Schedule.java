package com.belenot.mirea.schedule.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class Schedule {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    @NaturalId
    private String groupName;
    //Dangerous spot: PERSIST. What if some childs already persisted?
    @ManyToMany( mappedBy = "schedules" )
    private List<ScheduledSubject> scheduledSubjects;
    public int getId() {
	return id;
    }
    public Schedule setId(int id) {
	this.id = id;
	return this;
    }
    public String getGroupName() {
	return groupName;
    }
    public Schedule setGroupName(String groupName) {
	this.groupName = groupName;
	return this;
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public Schedule setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
	return this;
    }
    public void addScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.add(scheduledSubject);
	scheduledSubject.getSchedules().add(this);
    }
    public void removeScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.remove(scheduledSubject);
	scheduledSubject.getSchedules().remove(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	Schedule schedule = (Schedule) obj;
	return Objects.equals(schedule.getGroupName(), getGroupName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }
}
