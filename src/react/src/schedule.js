import React from 'react'
import ReactDOM  from 'react-dom'

import Schedule from './component/Schedule'
import { StyledControlPanel } from './component/styles/StyledControlPanel'


import AppDispatcher from './AppDispatcher'
import appActions from './appActions'
import { serverApi } from './api/serverApi'
import ScheduleStore from './store/ScheduleStore'
import ControlPanelStore from './store/ControlPanelStore'

window.React = React;
var dispatcher = new AppDispatcher();
var api = serverApi()
var scheduleStore = new ScheduleStore(dispatcher, api)
var controlPanelStore = new ControlPanelStore(dispatcher, api)
var actions = appActions(dispatcher)
var filterIteration = 0;
actions = {...actions, 
    onFilter: filter => api.scheduledSubject(filter, (r) => {filterIteration++; renderSchedule(JSON.parse(r))}, (err) => alert(err)),
    hideControlPanel: (show) => renderControlPanel(null, !show)
};

var renderSchedule = (scheduledSubjects) => {
    ReactDOM.render(
	<Schedule {...{scheduledSubjects, filterIteration}} />,
	document.querySelector('#app')
    )
}
var renderControlPanel = (data, hidden) => {
    ReactDOM.render(
	<StyledControlPanel {...{...data, ...actions, ...api, hidden}} />,
	document.querySelector('#control-panel')
    )
}
window.onload = () => {
    api.studentGroup(null, (r) => renderControlPanel({studentGroups: JSON.parse(r)}));
    api.teacher(null, (r) => renderControlPanel({teachers: JSON.parse(r)}));
    api.subject(null, (r) => renderControlPanel({subjects: JSON.parse(r)}));
    api.classroom(null, (r) => renderControlPanel({classrooms: JSON.parse(r)}));
}

scheduleStore.on("LOAD_SCHEDULED_SUBJECT", () =>  renderSchedule(scheduleStore.state))
scheduleStore.on("ON_ITEM_CLICK", () =>  renderSchedule(scheduleStore.state))

