const origin = "/schedule/api"
const apiEntries = {
    studentGroup: origin + "/studentgroup",
    updateStatus: origin + "/update/status",
    teacher: origin + "/teacher",
    subject: origin + "/subject",
    classroom: origin + "/classroom",
    filter: origin + "/schedule/filter"
}
    
const serverApi = (dispatcher) => ({
    studentGroup: (id) => {
	let type = 'LOAD_STUDENT_GROUP'
	request(apiEntries.studentGroup + (id?`/${id}`:""),
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR')
	)
    },
    updateStatus: (id) => {
	let type = 'UPDATE_STATUS'
	request(apiEntries.updateStatus,
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR')
	)
    },
    teacher: (id) => {
	let type = 'LOAD_TEACHER'
	request(apiEntries.teacher + (id?`/${id}`:""),
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR')
	)
    },
    subject: (id) => {
	let type = 'LOAD_SUBJECT'
	request(apiEntries.subject + (id?`/${id}`:""),
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR')
	)
    },
    classroom: (id) => {
	let type = 'LOAD_CLASSROOM'
	request(apiEntries.classroom + (id?`/${id}`:""),
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR')
	)
    },
    scheduledSubject: (scheduledSubjectFilter) =>{
	let type = 'LOAD_SCHEDULED_SUBJECT'
	request(apiEntries.filter,
		callback(dispatcher, type, 'OK', true),
		callback(dispatcher, type, 'ERR'),
		scheduledSubjectFilter
	)
    }	
})

const request = (address, callback, errorHandler=f=>f, body, method="get") => {
    let xhr = new XMLHttpRequest();
    method = typeof body === 'object' ? "post" : "get"
    xhr.open(method, address)
    if (method === 'post') {
	xhr.setRequestHeader('Content-Type', 'application/json')
    }
    xhr.onload = () => xhr.status === 200 ? callback(xhr.responseText) : errorHandler(xhr.responseText)
    xhr.send(typeof body === 'object' ? JSON.stringify(body) : null)
}

const callback = (dispatcher, type, status, isJSON) => (response) => {
    dispatcher.handleApi({
	type,
	status,
	data: isJSON?JSON.parse(response):response
    })
}

export default serverApi
