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
public class Teacher {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    private String name;
    private String surname;
    private String patronymic;
    @NaturalId
    private String shortName;
    @OneToMany( mappedBy = "teacher", orphanRemoval = true )
    @JsonIgnore
    private List<ScheduledSubject> scheduledSubjects = new ArrayList<>();
    public int getId() {
	return id;
    }
    public Teacher setId(int id) {
	this.id = id;
	return this;
    }
    public String getName() {
	return name;
    }
    public Teacher setName(String name) {
	this.name = name;
	return this;
    }
    public String getSurname() {
	return surname;
    }
    public Teacher setSurname(String surname) {
	this.surname = surname;
	return this;
    }
    public String getPatronymic() {
	return patronymic;
    }
    public Teacher setPatronymic(String patronymic) {
	this.patronymic = patronymic;
	return this;
    }
    public String getShortName() {
	return shortName;
    }
    public Teacher setShortName(String shorName) {
	this.shortName = shorName;
	return this;
    }
    public List<ScheduledSubject> getScheduledSubjects() {
	return scheduledSubjects;
    }
    public void setScheduledSubjects(List<ScheduledSubject> scheduledSubjects) {
	this.scheduledSubjects = scheduledSubjects;
    }
    public void addScheduledSubject(ScheduledSubject scheduleSubject) {
	scheduledSubjects.add(scheduleSubject);
	scheduleSubject.setTeacher(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	Teacher teacher = (Teacher) obj;
	return Objects.equals(teacher.getShortName(), getShortName());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getShortName());
    }
    
}
