import React from 'react'
import ReactDOM  from 'react-dom'

import Schedule from './component/Schedule'
import { StyledControlPanel } from './component/styles/StyledControlPanel'


import AppDispatcher from './AppDispatcher'
import appActions from './appActions'
import serverApi from './api/serverApi'
import ScheduleStore from './store/ScheduleStore'
import ControlPanelStore from './store/ControlPanelStore'

window.React = React;
var dispatcher = new AppDispatcher();
var api = serverApi(dispatcher)
var scheduleStore = new ScheduleStore(dispatcher, api)
var controlPanelStore = new ControlPanelStore(dispatcher, api)
var actions = appActions(dispatcher)

var renderSchedule = (data) => {
    ReactDOM.render(
	<Schedule {...{...data, actions}} />,
	document.querySelector('#app')
    )
}

var renderControlPanel = (data) => {
    ReactDOM.render(
	<StyledControlPanel {...{...data, ...actions}} />,
	document.querySelector('#control-panel')
    )
}

//window.onload = () => api.getGroups((r) => rerenderControlPanel({groups: r}), (msg)=>alert(msg))

//scheduleStore.on('SCHEDULE', () => rerenderSchedule(scheduleStore.getSchedule()))
window.onload = () => {
    api.studentGroup()
    api.teacher()
    api.subject()
    api.classroom()
}

scheduleStore.on("LOAD_SCHEDULED_SUBJECT", () =>  renderSchedule(scheduleStore.state))
scheduleStore.on("ON_ITEM_CLICK", () =>  renderSchedule(scheduleStore.state))
controlPanelStore.on('LOAD_SUBJECT', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('LOAD_TEACHER', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('LOAD_CLASSROOM', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('LOAD_STUDENT_GROUP', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('ON_ADD_DATE_INTERVAL', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('ON_REMOVE_DATE_INTERVAL', () => renderControlPanel(controlPanelStore.state))
controlPanelStore.on('CHANGE_FILTER', () => renderControlPanel(controlPanelStore.state))

