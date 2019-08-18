import { EventEmitter } from 'events'
import moment from 'moment'
const dateFormat = 'YYYY-MM-DD'

class ControlPanelStore extends EventEmitter {
    constructor(dispatcher, api, studentGroups=[], teachers=[], subjects=[], classrooms=[]) {
	super();
	this.dispatcherIndex = dispatcher.register(this.dispatch)
	this.api = api;
	this._state = {
	    teachers,
	    subjects,
	    classrooms,
	    studentGroups,
	    filter: {
		teachersIds: [],
		studentGroupsIds: [],
		subjectsIds: [],
		classroomsIds: [],
		lessonTimes: [],
		lessonTypes: [],
		dateIntervals: []
	    }
	}
    }
    
    get state() { return this._state }

    dispatch = (payload) => {
	const { type, data, status } = payload.action
	const source = payload.source

	switch (type) {
	    case 'ON_ITEM_CLICK':
		//this.onItemClick(data.type, data.id);
		//this.emit(type);
		console.log(type)
		break;
		
	    case 'LOAD_SUBJECT':
		if (status == 'OK' && data.length > 1) {
		    this._state = {
			...this._state,
			subjects: [...data]
		    }
		} else {
		    console.log(`[${type}] ${status}: ${data}`)
		}
		this.emit(type)
		break;
		
	    case 'LOAD_TEACHER':
		if (status == 'OK' && data.length > 1) {
		    this._state = {
			...this._state,
			teachers: [...data]
		    }
		} else {
		    console.log(`[${type}] ${status}: ${data}`)
		}
		this.emit(type)
		break;
		
	    case 'LOAD_CLASSROOM':
		if (status == 'OK' && data.length > 1) {
		    this._state = {
			...this._state,
			classrooms: [...data]
		    }
		} else {
		    console.log(`[${type}] ${status}: ${data}`)
		}
		this.emit(type)
		break;
		
	    case 'LOAD_STUDENT_GROUP':
		if (status == 'OK' && data.length > 1) {
		    this._state = {
			...this._state,
			studentGroups: [...data]
		    }
		} else {
		    console.log(`[${type}] ${status}: ${data}`)
		}
		this.emit(type)
		break;
	    case 'ON_ADD_DATE_INTERVAL':
		this._state.filter = {
		    ...this._state.filter,
		    dateIntervals: [...dateIntervals, {
			value: {
			    firstValue: new Date(),
			    lastValue: new Date()
			}
		    }]
		}
		this.emit(type)
		break;
	    case 'ON_REMOVE_DATE_INTERVAL':
		let dateIntervals = this._state.filter.dateIntervals.filter( (di, i) => i != data);
		this._state.filter.dateIntervals= dateIntervals;
		this.emit(type);
		break;
		
	    case 'ON_FILTER':
		switch (data.status) {
		    case 'OK':
			if (data.status == 'OK')
			    this.api.scheduledSubject(data.filter)
			break;
		    case 'STUDENT_GROUPS':
			this._state.filter.studentGroupsIds = data.data.elements
			break;
		    case 'TEACHERS':
			this._state.filter.teachersIds = data.data.elements
			break;
		    case 'SUBJECTS':
			this._state.filter.subjectsIds = data.data.elements
			break;
		    case 'CLASSROOMS':
			this._state.filter.classroomsIds = data.data.elements
			break;
		    case 'LESSON_TIMES':
			this._state.filter.lessonTimes = data.data.elements
			break;
		    case 'LESSON_TYPES':
			this._state.filter.lessonTypes = data.data.elements
			break;
		    case 'ADD_DATE_INTERVAL':
			this._state.filter.dateIntervals =
			    [...this._state.filter.dateIntervals,
			     {value: {firstValue: new Date(), lastValue: new Date()}}]
			break;
		    case 'REMOVE_DATE_INTERVAL':
			this._state.filter.dateIntervals =
			    this._state.filter.dateIntervals
				.filter( (di, i) => i != data.data.id )
			break;
		    case 'FIRST_DATE_INTERVAL':
			this._state.filter.dateIntervals =
			    this._state.filter.dateIntervals
				.map( (di, i) => i == data.data.id? {
				    value: {
					firstValue: data.data.value,
					lastValue: di.value.lastValue
				    }
				}: di)
			break;
		    case 'LAST_DATE_INTERVAL':
			this._state.filter.dateIntervals =
			    this._state.filter.dateIntervals
				.map( (di, i) => i == data.data.id? {
				    value: {
					firstValue: di.value.firstValue,
					lastValue: data.data.value
				    }
				}: di)
			break;
		    default:
			return false;
		}
		break;
	}
	this.emit('CHANGE_FILTER')
    }
    
    onItemClick = (type, id) => {
	type = type + 's';
	this._state[type] = this._state[type].map( item => {
	    if (item.id == id) {
		if (!item._clicked) {
		    item = { ...item, _clicked: true }
		} else {
		    delete item._clicked;
		}
	    }
	    return item;
	})
    }
	
	    
}

    export default ControlPanelStore
