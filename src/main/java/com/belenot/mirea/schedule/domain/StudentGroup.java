package com.belenot.mirea.schedule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

@Entity
public class StudentGroup {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    @NaturalId
    private String groupName;

    @ManyToMany( mappedBy = "studentGroups" )
    @JsonIgnore
    private List<ScheduledSubject> scheduledSubjects = new ArrayList<>();
    public int getId() {
	return id;
    }
    public StudentGroup setId(int id) {
	this.id = id;
	return this;
    }
    public String getGroupName() {
	return groupName;
    }
    public StudentGroup setGroupName(String groupName) {
	this.groupName = groupName;
	return this;
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public StudentGroup setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
	return this;
    }
    public void addScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.add(scheduledSubject);
	scheduledSubject.getStudentGroups().add(this);
    }
    public void removeScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.remove(scheduledSubject);
	scheduledSubject.getStudentGroups().remove(this);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	StudentGroup studentGroup =  (StudentGroup) obj;
	return Objects.equals(studentGroup.getGroupName(), getGroupName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getGroupName());
    }
}
