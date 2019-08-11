const defaultString = "N/A";
const Subject = ({id, subject, date, classroom,teacher, lessonType, lessonTime}) => (
    <section className='subject'>
	<label className='lesson-time'>{lessonTime[1]}</label>
	<label className='title'>{subject.title}</label>
	<label className='lesson-type'>{lessonType}</label>
	<label className='classroom'>{"N/A" || classroom && classroom.number}</label>
	<label className='teacher'>{"N/A" || teacher && teacher.shortName}</label>
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
