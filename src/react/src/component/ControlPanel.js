const ControlPanel = ({groups={}, loadSchedule, filterSchedule}) => {
    let selectedGroup;
    return (
	<section id="control-panel">
	    <form onSubmit={ e => e.preventDefault()}>
		<select onChange={(e)=>loadSchedule({id: e.target.value})}>
		    {Object.keys(groups).map( id => 
			<option key={id} value={id}>{groups[id]}</option>
		    )}
		</select>
	    </form>
	</section>
    )
}
export default ControlPanel
