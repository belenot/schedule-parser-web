import { EventEmitter } from 'events'
import moment  from 'moment'
window.moment = moment
const dateFormat = 'YYYY-MM-DD'

class ScheduleStore extends EventEmitter {
    constructor(dispatcher, api, scheduledSubjects=[]) {
	super()
	this._state = { scheduledSubjects }
	this.dispatcherIndex = dispatcher.register(this.dispatch)
	this.api = api
    }

    get state() {
	return this._state;
    }

    dispatch = (payload) => {
	const {type, data, status} = payload.action;
	const source = payload.source
	switch (type) {
	    case 'LOAD_SCHEDULED_SUBJECT':
		if (status == 'OK') {
		    this._state = {
			...this.state,
			scheduledSubjects: [...data]
		    }
		} else {
		    console.log(`[${type}] ${status}: ${data}`)
		}
		this.emit(type)
		break;
		
	    case 'ON_ITEM_CLICK':
		let color = (Math.random()*360).toFixed();
		if (data.status == 'SUBJECT') {
		    this._state = { ...this.state, scheduledSubjects: this._state.scheduledSubjects.map( ss => {
			if (ss.subject.id == data.id) {
			    let newSS = {...ss}
			    if (!newSS.subject._clicked) {
				newSS.subject._clicked = true
				newSS.subject._backgroundColor = `linear-gradient(0deg, hsl(${color}, 20%, 60%), hsl(${color}, 20%, 75%))`
			    } else {
				delete newSS.subject._clicked
				delete newSS.subject._backgroundColor;
			    }
			    return newSS
			}
			return ss
		    })}
		}	    
		this.emit(type)
		break;
	}
    }

    onFilter = (scheduledSubjects) => {
	this._state = { ...this._state, scheduledSubjects }
	this.emit('ON_FILTER')
    }

    onItemClick = (type, id) => {
	this._state = { ...this._state,
			scheduledSubjects: this._state.scheduledSubjects.map( ss => {
			    if (ss[type] && ss[type].id == id) {
				if (!ss[type]._clicked) {
				    ss[type] = { ...ss[type], _clicked: true }
				}
				else {
				    delete item._clicked;
				}
			    }
			    return ss;
			})
	}
	this.emit('ON_ITEM_CLICK');
    }
}

export default ScheduleStore
