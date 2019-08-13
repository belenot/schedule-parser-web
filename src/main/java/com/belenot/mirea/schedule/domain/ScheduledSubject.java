package com.belenot.mirea.schedule.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.ParamDef;

@Entity
@NamedQuery( name = "ScheduledSubject_byStudentGroup",
	     query = "select ss from ScheduledSubject ss inner join ss.studentGroups sg where sg.id in (:studentGroupsIds)"
	     )
//@SecondaryTable( name = "ScheduledSubject_StudentGroup",  )
@FilterDefs({
	@FilterDef( name = "classroomFilter", parameters = @ParamDef( name = "classroomsIds", type = "integer" ) ),
	    @FilterDef( name = "subjectFilter", parameters = @ParamDef( name = "subjectsIds", type = "integer" ) ),
	    @FilterDef( name = "teacherFilter", parameters = @ParamDef( name = "teachersIds", type = "integer" ) ),
	    @FilterDef( name = "lessonTypeFilter", parameters = @ParamDef( name = "lessonTypes", type = "string" ) ),
	    @FilterDef( name = "lessonTimeFilter", parameters = @ParamDef( name = "lessonTimes", type = "string" ) ),
	    @FilterDef( name = "dateFilter", parameters = { @ParamDef( name = "dateFirst", type = "date"), @ParamDef( name = "dateLast", type = "date") } ),

	    @FilterDef( name = "notClassroomFilter", parameters = @ParamDef( name = "classroomsIds", type = "integer" ) ),
	    @FilterDef( name = "notSubjectFilter", parameters = @ParamDef( name = "subjectsIds", type = "integer" ) ),
	    @FilterDef( name = "notTeacherFilter", parameters = @ParamDef( name = "teachersIds", type = "integer" ) ),
	    @FilterDef( name = "notLessonTypeFilter", parameters = @ParamDef( name = "lessonTypes", type = "string" ) ),
	    @FilterDef( name = "notLessonTimeFilter", parameters = @ParamDef( name = "lessonTimes", type = "string" ) ),
	    @FilterDef( name = "notDateFilter", parameters = { @ParamDef( name = "dateFirst", type = "date"), @ParamDef( name = "dateLast", type = "date") } )
	    })
@Filters({
	@Filter( name = "classroomFilter", condition = "classroom_id in (:classroomsIds)" ),
	    @Filter( name = "subjectFilter", condition = "subject_id in (:subjectsIds)" ),
	    @Filter( name = "teacherFilter", condition = "teacher_id in (:teachersIds)" ),
	    @Filter( name = "lessonTypeFilter", condition = "lessonType in (:lessonTypes)" ),
	    @Filter( name = "lessonTimeFilter", condition = "lessonTime in (:lessonTimes)" ),
	    @Filter( name = "dateFilter", condition = "date between :dateFirst and :dateLast" ),
	    
	    @Filter( name = "notClassroomFilter", condition = "classroom_id not in (:classroomsIds)" ),
	    @Filter( name = "notSubjectFilter", condition = "subject_id not in (:subjectsIds)" ),
	    @Filter( name = "notTeacherFilter", condition = "teacher_id not in (:teachersIds)" ),
	    @Filter( name = "notLessonTypeFilter", condition = "lessonType not in (:lessonTypes)" ),
	    @Filter( name = "notLessonTimeFilter", condition = "lessonTime not in (:lessonTimes)" ),
	    @Filter( name = "notDateFilter", condition = "date not between :dateFirst and :dateLast" )
	    })
	    		 
public class ScheduledSubject {
    
    public enum LessonType {
	LECTION, PRACTICE, LAB;
	public static LessonType byString(String str) {
	    switch (str) {
	    case "лек": return LECTION;
	    case "пр": return PRACTICE;
	    case "лр": return LAB;
	    }
	    return null;
	}
    }

    public enum LessonTime {
	L_DEFAULT("00:00"),
	L1("9:00"),  L2("10:40"), L3("13:00"),
	L4("14-40"), L5("16:20"), L6("18:00");

	private Date date;
	private DateFormat lessonTimeFormat = new SimpleDateFormat("HH:mm");
	
	LessonTime(String date) {
	    try {
		this.date = lessonTimeFormat.parse(date);
	    } catch (ParseException exc) {
		this.date = null;
	    }
	}

	public String getTime() {
	    return date != null ? lessonTimeFormat.format(date) : null;
	}

	public static LessonTime byNumber(int number) {
	    switch (number) {
	    case 1: return LessonTime.L1;
	    case 2: return LessonTime.L2;
	    case 3: return LessonTime.L3;
	    case 4: return LessonTime.L4;
	    case 5: return LessonTime.L5;
	    case 6: return LessonTime.L6;
	    default: return LessonTime.L_DEFAULT;
	    }
	}
    }
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE )
    private int id;
    @ManyToOne
    @NaturalId
    private Subject subject;
    @NaturalId
    private Date date;
    @ManyToOne
    @NaturalId
    private Classroom classroom;
    @ManyToOne( cascade = CascadeType.PERSIST )
    @NaturalId
    private Teacher teacher;
    @ManyToMany( fetch = FetchType.EAGER )
    private List<StudentGroup> studentGroups = new ArrayList<>();
    @NaturalId
    @Enumerated( EnumType.STRING )
    private LessonType lessonType;
    @NaturalId
    @Enumerated( EnumType.STRING )
    private LessonTime lessonTime;
    
    public int getId() {
	return id;
    }
    public ScheduledSubject setId(int id) {
	this.id = id;
	return this;
    }
    public Subject getSubject() {
	return subject;
    }
    public ScheduledSubject setSubject(Subject subject) {
	this.subject = subject;
	return this;
    }
    public Date getDate() {
	return date;
    }
    public ScheduledSubject setDate(Date date) {
	this.date = date;
	return this;
    }
    public Classroom getClassroom() {
	return classroom;
    }
    public ScheduledSubject setClassroom(Classroom classroom) {
	this.classroom = classroom;
	return this;
    }
    public Teacher getTeacher() {
	return teacher;
    }
    public ScheduledSubject setTeacher(Teacher teacher) {
	this.teacher = teacher;
	return this;
    }
    public LessonType getLessonType() {
	return lessonType;
    }
    public ScheduledSubject setLessonType(LessonType lessonType) {
	this.lessonType = lessonType;
	return this;
    }

    public LessonTime getLessonTime() {
	return lessonTime;
    }
    public ScheduledSubject setLessonTime(LessonTime lessonTime) {
	this.lessonTime = lessonTime;
	return this;
    }
    public List<StudentGroup> getStudentGroups() {
	return studentGroups;
    }
    public void setStudentGroups(List<StudentGroup> studentGroups) {
	this.studentGroups = studentGroups;
    }
    public void addStudentGroup(StudentGroup studentGroup) {
        studentGroups.add(studentGroup);
        studentGroup.getScheduledSubjects().add(this);
    }
    public void removeStudentGroup(StudentGroup studentGroup) {
        studentGroups.remove(studentGroup);
        studentGroup.getScheduledSubjects().remove(this);
    }

    @Override
    public String toString() {
	String format = "Schedules Subject\nДата:\t\t%s\nВремя:\t\t%s\nПредмет:\t%s\nВид занятий:\t%s\nПреподаватель:\t%s\nАудитория:\t%s";
	return String.format(format,
			     date != null ? date.toString() : null,
			     lessonTime.getTime(),
			     subject.getTitle(),
			     lessonType != null ? lessonType.name() : null,
			     teacher != null ? teacher.getShortName() : null,
			     classroom != null ? classroom.getNumber() : null);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
	if (obj == null || obj.getClass() != getClass()) return false;
	ScheduledSubject scheduledSubject = (ScheduledSubject) obj;
	boolean subjectEq = Objects.equals(scheduledSubject.getSubject(), getSubject());
	boolean dateEq = Objects.equals(scheduledSubject.getDate(), getDate());
	boolean classroomEq = Objects.equals(scheduledSubject.getClassroom(), getClassroom());
	boolean teacherEq = Objects.equals(scheduledSubject.getTeacher(), getTeacher());
	boolean lessonTypeEq = Objects.equals(scheduledSubject.getLessonType(), getLessonType());
	boolean lessonTimeEq = Objects.equals(scheduledSubject.getLessonTime(), getLessonTime());
	return subjectEq && dateEq && classroomEq && teacherEq && lessonTypeEq && lessonTimeEq;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSubject(), getDate(), getClassroom(), getTeacher(), getLessonType(), getLessonTime());
    }
}
