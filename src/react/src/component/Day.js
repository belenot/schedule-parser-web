import Subject from './Subject'

const Day = ({scheduledSubjects, date, actions}) => (
    <section className="day">
	<label className='date'>{date}</label>
	<div className='subject header'>
	    <label>№</label>
	    <label>Название</label>
	    <label>Вид</label>
	    <label>Кабинет</label>
	    <label>Преподаватель</label>
	</div>
	{scheduledSubjects.sort( (s1, s2) => s1.lessonTime[1] - s2.lessonTime[1]).map( (subject, i) =>
	    <Subject key={i} {...subject} onItemClick={actions.onItemClick} />
	)}
    </section>
)

export default Day
