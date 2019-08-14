const origin = "/schedule/api"
const apiEntries = {
    studentGroup: origin + "/studentgroup",
    updateStatus: origin + "/update/status",
    teacher: origin + "/teacher",
    subject: origin + "/subject",
    classroom: origin + "/classroom",
    filter: origin + "/schedule/filter"
}
    
const serverApi = {
    studentGroup: (handler, errHandler, id) => {
	request(apiEntries.studentGroup + (id?`/${id}`:""), r => handler(JSON.parse(r)), errHandler)
    },
    updateStatus: (handler, errHandler, id) => {
	request(apiEntries.updateStatus, r => handler(JSON.parse(r)), errHandler)
    },
    teacher: (handler, errHandler, id) => {
	request(apiEntries.teacher + (id?`/${id}`:""), r => handler(JSON.parse(r)), errHandler)
    },
    subject: (handler, errHandler, id) => {
	request(apiEntries.subject + (id?`/${id}`:""), r => handler(JSON.parse(r)), errHandler)
    },
    classroom: (handler, errHandler, id) => {
	request(apiEntries.classroom + (id?`/${id}`:""), r => handler(JSON.parse(r)), errHandler)
    },
    scheduledSubject: (handler, errHandler, scheduledSubjectFilter) =>{
	request(apiEntries.filter, r => handler(JSON.parse(r)), errHandler, scheduledSubjectFilter)
    }	
}

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

export default serverApi
