import { StyledSelect } from './styles/StyledSelect';
import { StyledDateInterval } from './styles/StyledDateInterval';
import { StyledCheckboxGroup } from './styles/StyledCheckboxGroup'
import { lessonTimes, lessonTypes, displayedLessonTime, displayedLessonType } from '../helpers'

const ControlPanel = ({ className, studentGroups=[], teachers = [],
			subjects = [], classrooms = [], filter = {},
			onFilter=f=>f
}) => {
	const handleSelect = () => onFilter();
	
	return (
		<section id="control-panel" className={className}>
		<form onSubmit={ e => { e.preventDefault(); onFilter({status: 'OK'}) }}>
			{[
				[studentGroups, sg => sg.groupName, (id, excluded) => handleFilter('STUDENT_GROUPS', {id, excluded}), "Группа"], 
				[teachers, t => t.shortName, (id, excluded) => handleFilter('TEACHERS', {id, excluded}), "Преподаватель"],
				[subjects, s => s.title, (id, excluded) => handleFilter('SUBJECTS', {id, excluded}), "Предмет"],
				[classrooms, c => c.number, (id, excluded) => handleFilter('CLASSROOMS', {id, excluded}), "Кабинет"],
			].map( ([options, display, handleSelect, placeholder], i) => 
				<StyledSelect key={i} {...{options, display, handleSelect, placeholder}} />
			)}
			<hr/>
			<div className='checkbox-groups'>
				{[
					[lessonTimes, time => displayedLessonTime[time], (id, excluded) => handleFilter('LESSON_TIMES', {id, excluded}), "lessonTimes"],
					[lessonTypes, type => displayedLessonType[type], (id, excluded) => handleFilter('LESSON_TYPES', {id, excluded}), "lessonTypes"]
				].map( ([options, display, handleCheck, name], i) => 
					<StyledCheckboxGroup key={name} {...{options, display, handleCheck, name}} />
				)}
			</div>
			<hr/>
			<div className="date-intervals">
			{filter.dateIntervals.map ( (dateInterval, i) => 
				<StyledDateInterval key={i} dateInterval={dateInterval} handleDate={(id, excluded) => handleFilter('DATE', {id, exlucded})} />
			)}
			<button onClick={ e => { e.preventDefault(); onFilter({status: 'ADD_DATE_INTERVAL', data: {}}) } }>+ Интервал</button>
			</div>
			<button className="filter-btn">filter</button>
		</form>
		</section>
	)
}


export default ControlPanel
