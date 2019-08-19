import { StyledDay } from './styles/StyledDay'
import { randomColor } from '../helpers';
import { useState, useEffect } from 'react';

const Schedule = ({scheduledSubjects, filterIteration}) => {
	const [scheduleState, setScheduleState] = useState(scheduledSubjects);
	useEffect( () => setScheduleState(scheduledSubjects), [filterIteration]);

	let color = randomColor();
	const onSubjectClick = (id => setScheduleState(scheduleState.map( ss => {
		if (ss.subject.id == id) {
			if (ss._clicked) {
				let newSS = {...ss};
				delete newSS._clicked;
				delete newSS._color;
				return newSS;
			} else {
				return {...ss, _clicked: true, _color: color};
			}
		} else {
			return ss;
		}
	})))

    let grouppedScheduledSubjects = scheduleState.reduce( (ac, n) => {
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
		<StyledDay key={i} date={date} scheduledSubjects={grouppedScheduledSubjects[date]} onSubjectClick={onSubjectClick} />
	    )}
	</section>
    )
}

export default Schedule

    
