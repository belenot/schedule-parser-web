import { EventEmitter } from 'events'
import moment  from 'moment'
window.moment = moment
const dateFormat = 'YYYY-MM-DD'

class ScheduleStore extends EventEmitter {
    constructor(dispatcher, api, schedule={}) {
	super()
	this.schedule = schedule
	this.dispatcherIndex = dispatcher.register(this.dispatch)
	this.api = api
    }

    getSchedule = () => {
	return this.schedule;
    }

    dispatch = (payload) => {
	const {type, data} = payload.action;
	switch (type) {
	    case 'LOAD_SCHEDULE':
		this.api.loadSchedule(data.id, this.loadSchedule, (msg) => alert(msg))
		break;
	}
    }

    loadSchedule = (schedule) => {
	this.schedule = schedule;
	this.emit('SCHEDULE')
    }
	
    
    

}

export default ScheduleStore
