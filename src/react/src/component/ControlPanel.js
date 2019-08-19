import { StyledSelect } from './styles/StyledSelect';
import { StyledDateInterval } from './styles/StyledDateInterval';
import { StyledCheckboxGroup } from './styles/StyledCheckboxGroup'
import { lessonTimes, lessonTypes, displayedLessonTime, displayedLessonType, curDateInterval } from '../helpers'
import { useFilter } from '../hooks/useFilter';

const ControlPanel = ({ className, studentGroups=[], teachers = [],
			subjects = [], classrooms = [], hidden,
			onFilter=f=>f, hideControlPanel=f=>f
}) => {
	const [filter, selections, handleFilter] = useFilter(filter, {studentGroups, teachers, subjects, classrooms});
	var {studentGroups, teachers, subjects, classrooms} = selections;

	return (
		<section id="control-panel" className={className}>
			<form onSubmit={ e => { e.preventDefault(); onFilter(filter) }}>
				<div>
					<button className="filter-btn">filter</button>
					<button className="filter-btn hide-btn" onClick={e => {e.preventDefault(); hideControlPanel(hidden)}}></button>
				</div>
				{[
					[studentGroups, filter.studentGroupsIds, sg => sg.groupName, (type, id, excluded) => handleFilter(type, {dest: "studentGroupsIds"}, {value: id, excluded}), "Группа"], 
					[teachers, filter.teachersIds, t => t.shortName, (type, id, excluded) => handleFilter(type, {dest: "teachersIds"}, {value: id, excluded}), "Преподаватель"],
					[subjects, filter.subjectsIds, s => s.title, (type, id, excluded) => handleFilter(type, {dest: "subjectsIds"}, {value: id, excluded}), "Предмет"],
					[classrooms, filter.classroomsIds, c => c.number, (type, id, excluded) => handleFilter(type, {dest: "classroomsIds"}, {value: id, excluded}), "Кабинет"],
				].map( ([options, selectedOptions, display, handleSelect, placeholder], i) => 
					<StyledSelect key={i} {...{options, selectedOptions, display, handleSelect, placeholder}} />
				)}
				<hr/>
				<div className='checkbox-groups'>
					{[
						[lessonTimes, time => displayedLessonTime[time], (value, excluded, checked) => handleFilter(!checked?'REMOVE':'ADD', {dest: 'lessonTimes'}, {value, excluded}), "lessonTimes"],
						[lessonTypes, type => displayedLessonType[type], (value, excluded, checked) => handleFilter(!checked?'REMOVE':'ADD', {dest: 'lessonTypes'}, {value, excluded}), "lessonTypes"]
					].map( ([options, display, handleCheck, name], i) => 
						<StyledCheckboxGroup key={name} {...{options, display, handleCheck, name}} />
					)}
				</div>
				<hr/>
				<div className="date-intervals">
					{filter.dateIntervals.map ( (dateInterval, i) => 
						<StyledDateInterval key={i} dateInterval={dateInterval} handleDate={(dateInterval, excluded, remove) => handleFilter(remove?'REMOVE':'CHANGE', {dest: 'dateIntervals', index: i}, {...dateInterval})} />
					)}
				</div>
				<button onClick={ e => { e.preventDefault(); handleFilter('ADD', {dest: 'dateIntervals'}, curDateInterval()) } }>+ Интервал</button>
				<hr/>
			</form>
		</section>
	)
}


export default ControlPanel
