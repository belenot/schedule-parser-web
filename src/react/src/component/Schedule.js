import Day from './Day'

const Schedule = ({groupName, scheduledSubjects}) => {
    let grouppedScheduledSubjects = scheduledSubjects.reduce( (ac, n) => {
	if (ac[n.date]) {
	    ac[n.date] = [...ac[n.date], n]
	} else {
	    ac[n.date] = [n]
	}
	return ac;
    }, {});
    return (
	<section className="schedule">
	    <h1>{groupName}</h1>
	    {Object.keys(grouppedScheduledSubjects).sort( (d1, d2) => new Date(d1) - new Date(d2)).map( (date, i) =>
		<Day key={i} date={date} scheduledSubjects={grouppedScheduledSubjects[date]} />
	    )}
	</section>
    )
}

export default Schedule

    
