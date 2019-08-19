import { StyledSubject } from './styles/StyledSubject'

const Day = ({className, scheduledSubjects, date, onSubjectClick=f=>f}) => (
    <section className={className}>
		<label className='date'>{date}</label>
		{[1,2,3,4,5,6].map( lessonTime => {
			let scheduleSubject = scheduledSubjects.find( s => s.lessonTime[1] == lessonTime);
			if (scheduleSubject) {
				return (
					<StyledSubject key={lessonTime} {...scheduleSubject} onSubjectClick={onSubjectClick} />
				)
			} else {
				return (
					<StyledSubject key={lessonTime} subject={{title: " "}} lessonTime={'L' + lessonTime} onSubjectClick={f=>f} />
				)
			}
		})}
    </section>
)

export default Day
