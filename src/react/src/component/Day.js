import Subject from './Subject'

const Day = ({scheduledSubjects, date}) => (
    <section className="day">
	<label className='date'>{date}</label>
	<div class='subject header'>
	    <label>№</label>
	    <label>Название</label>
	    <label>Вид</label>
	    <label>Кабинет</label>
	    <label>Преподаватель</label>
	</div>
	{scheduledSubjects.map( (subject, i) =>
	    <Subject key={i} {...subject} />
	)}
    </section>
)

export default Day
