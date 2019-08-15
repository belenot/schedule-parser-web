const ControlPanel = ({ studentGroups=[], teachers = [],
			subjects = [], classrooms = [], filter = {},
			onFilter=f=>f
}) => (
    <section id="control-panel">
	<form onSubmit={ e => { e.preventDefault(); onFilter({status: 'OK'}) }}>
	    {[['STUDENT_GROUPS', studentGroups, 'groupName'], ['TEACHERS', teachers, 'shortName'],
	      ['SUBJECTS', subjects, 'title'], ['CLASSROOMS', classrooms, 'number']]
		.map( s =>
		  <select key={s[0]} multiple onChange={ e => onFilter({
		      status: s[0],
		      data: {
			  elements: Array.from(e.target.options).filter( opt => opt.selected ).map( opt => ({value: opt.value}))
		      }
		  })}>
		      { s[1].map( el =>
			  <option key={el.id} value={el.id}>{el[s[2]]}</option>
		      )}
		  </select>
	      )}
	    <select  multiple onChange={ e => onFilter({
		status: 'LESSON_TIMES',
		data: {
		    elements: Array.from(e.target.options)
				   .filter( op => op.selected )
				   .map( op => ({value: op.value}))
		}
	    }) }>
		{['L1', 'L2', 'L3', 'L4', 'L5', 'L6'].map( (lt, i) =>
		    <option key={lt} value={ lt }>{i + 1 + "пара"}</option>
		)}
	    </select>
	    <select multiple onChange={ e => onFilter({
		status: 'LESSON_TYPES',
		data: {
		    elements: Array.from(e.target.options)
				   .filter( op => op.selected)
				   .map( op => ({value: op.value}))
		}
	    }) }>
		{['LECTION', 'PRACTICE', 'LAB'].map( lt =>
		    <option key={lt} value={lt}>{lt}</option>
		)}
	    </select>
	    <div className="date-intervals">
		{ filter.dateIntervals.map ( (di, i) =>
		    <div key={i}>
			<input type="date"
			       value={ di.value.firstValue}
			       onChange={ e=> onFilter({status: 'FIRST_DATE_INTERVAL', data: {id: i, value: e.target.value }}) }/>
			<input type="date"
			       value={ di.value.lastValue }
			       onChange={ e=> onFilter({status: 'LAST_DATE_INTERVAL', data: {id: i, value: e.target.value }}) }/>
			<button onClick={ e => { e.preventDefault(); onFilter({status: 'REMOVE_DATE_INTERVAL', data: {id: i}}) } } />
		    </div>
		)}
		<button onClick={ e => { e.preventDefault(); onFilter({status: 'ADD_DATE_INTERVAL', data: {}}) } } />
	    </div>
	    <button>filter</button>
	</form>
    </section>
)


export default ControlPanel
