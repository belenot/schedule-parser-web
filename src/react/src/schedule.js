import React from 'react'
import ReactDOM  from 'react-dom'

import Schedule from './component/Schedule'
import ControlPanel from './component/ControlPanel'


import AppDispatcher from './AppDispatcher'
import appActions from './appActions'
import api from './api/serverApi'
import ScheduleStore from './store/ScheduleStore'

window.React = React;
window.api = api
//var dispatcher = new AppDispatcher();
//var scheduleStore = new ScheduleStore(dispatcher, api)
//var actions = appActions(dispatcher)

var rerenderSchedule = (schedule) => {
    ReactDOM.render(
	<Schedule {...{...schedule, ...actions}} />,
	document.querySelector('#app')
    )
}

var rerenderControlPanel = (data) => {
    ReactDOM.render(
	<ControlPanel {...{...data, ...actions}} />,
	document.querySelector('#control-panel')
    )
}

//window.onload = () => api.getGroups((r) => rerenderControlPanel({groups: r}), (msg)=>alert(msg))

//scheduleStore.on('SCHEDULE', () => rerenderSchedule(scheduleStore.getSchedule()))
