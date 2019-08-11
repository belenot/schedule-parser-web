package com.belenot.mirea.schedule.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.NaturalId;

@Entity
public class Subject {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    @NaturalId
    private String title;
    @OneToMany( mappedBy = "subject", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private List<ScheduledSubject> scheduledSubjects = new ArrayList<>();
    public int getId() {
	return id;
    }
    public Subject setId(int id) {
	this.id = id;
	return this;
    }
    public String getTitle() {
	return title;
    }
    public Subject setTitle(String title) {
	this.title = title;
	return this;
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public void setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
    }
    public void addScheduledSubject(ScheduledSubject scheduledSubject) {
	scheduledSubjects.add(scheduledSubject);
	scheduledSubject.setSubject(this);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	Subject subject = (Subject) obj;
	return Objects.equals(subject.getTitle(), getTitle());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getTitle());
    }
    
}
