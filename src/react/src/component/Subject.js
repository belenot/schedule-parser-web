const defaultString = "N/A";
const Subject = ({id, subject, date, classroom,teacher, lessonType, lessonTime, onItemClick=f=>f}) => (
    <section className='subject' style={subject._clicked?{background: subject._backgroundColor}:undefined}>
	<label className='lesson-time'>{lessonTime[1]}</label>
	<label className='title' onClick={ e => onItemClick({status: 'SUBJECT', id: subject.id})}>{subject.title}</label>
	<label className='lesson-type'>{lessonType}</label>
	<label className='classroom'>{classroom && classroom.number || "N/A"}</label>
	<label className='teacher'>{teacher && teacher.shortName || "N/A"}</label>
    </section>
)
Subject.defaultProps = {
    id: 0,
    subject: {title: defaultString},
    date: defaultString,
    classroom: {number: defaultString},
    teacher: {shortName: defaultString},
    lessonType: defaultString,
    lessonTime:defaultString
}

export default Subject
