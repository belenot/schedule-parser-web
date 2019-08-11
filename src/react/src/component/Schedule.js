import Day from './Day'

const Schedule = ({groupName, subjects}) => {
    let grouppedSubjects = subjects.reduce( (ac, n) => {
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
	    {Object.keys(grouppedSubjects).sort( (d1, d2) => new Date(d1) - new Date(d2)).map( (date, i) =>
		<Day key={i} date={date} subjects={grouppedSubjects[date]} />
	    )}
	</section>
    )
}

export default Schedule

    
