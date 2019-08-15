import Day from './Day'

const Schedule = ({scheduledSubjects, actions}) => {
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
	    {Object.keys(grouppedScheduledSubjects).sort( (d1, d2) => new Date(d1) - new Date(d2)).map( (date, i) =>
		<Day key={i} date={date} scheduledSubjects={grouppedScheduledSubjects[date]} actions={actions} />
	    )}
	</section>
    )
}

export default Schedule

    
