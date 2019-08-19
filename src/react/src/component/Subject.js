import {displayedLessonType} from '../helpers'

const defaultString = "N/A";
const Subject = ({className, id, subject, date, classroom, teacher, lessonType, lessonTime, studentGroups=[], _clicked, _color, onSubjectClick=f=>f}) => (
    <section className={className} style={_clicked?{backgroundColor: _color}:undefined}  onClick={ e => onSubjectClick(subject.id) }>
        <label className='lesson-time'>{lessonTime[1]}</label>
        <label className='title'>{subject.title}</label>
        <div className="subject-info">
            {lessonType ?  <label className='lesson-type'>{displayedLessonType[lessonType]}</label> : null }
            {classroom && classroom.number ? <label className='classroom'>{classroom.number}</label> : null }
            {teacher && teacher.shortName ? <label className='teacher'>{teacher.shortName}</label> : null }
        </div>
        <div className="student-groups">
            {studentGroups.map( (sg, i) => 
                <div>
                    <label>{i + 1}</label>
                    <label>{' ' + sg.groupName}</label>
                </div>
            )}
        </div>
    </section>
)
Subject.defaultProps = {
    id: 0,
    subject: {title: defaultString},
    date: defaultString
}

export default Subject
