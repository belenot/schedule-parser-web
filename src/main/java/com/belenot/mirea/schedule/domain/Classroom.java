package com.belenot.mirea.schedule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

@Entity
public class Classroom {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    @NaturalId
    private String location;
    @NaturalId
    private String number;
    @OneToMany( mappedBy = "classroom", orphanRemoval = true )
    @JsonIgnore
    private List<ScheduledSubject> scheduledSubjects = new ArrayList<>();
    
    
    public int getId() {
	return id;
    }
    public Classroom setId(int id) {
	this.id = id;
	return this;
    }
    public String getLocation() {
	return location;
    }
    public Classroom setLocation(String location) {
	this.location = location;
	return this;
    }
    public String getNumber() {
	return number;
    }
    public Classroom setNumber(String number) {
	this.number = number;
	return this;
    }
    public void addScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.add(scheduledSubject);
	scheduledSubject.setClassroom(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	Classroom classroom = (Classroom) obj;
	return Objects.equals(classroom.getLocation(), getLocation()) && Objects.equals(classroom.getNumber(), getNumber());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getLocation(), getNumber());
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public void setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
    }
    
}
