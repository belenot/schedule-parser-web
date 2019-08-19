const origin = "/schedule/api"
const apiEntries = {
    studentGroup: origin + "/studentgroup",
    updateStatus: origin + "/update/status",
    teacher: origin + "/teacher",
    subject: origin + "/subject",
    classroom: origin + "/classroom",
    filter: origin + "/schedule/filter"
}
    
export const serverApi = () => ({
    studentGroup: (id, handler, errHandler) => {
		request(apiEntries.studentGroup + (id?`/${id}`:""), handler, errHandler);
	},
    teacher: (id, handler, errHandler) => {
		request(apiEntries.teacher + (id?`/${id}`:""), handler, errHandler);
    },
    subject: (id, handler, errHandler) => {
		request(apiEntries.subject + (id?`/${id}`:""), handler, errHandler);
    },
    classroom: (id, handler, errHandler) => {
		request(apiEntries.classroom + (id?`/${id}`:""), handler, errHandler);
    },
    scheduledSubject: (scheduledSubjectFilter, handler, errHandler) =>{
		request(apiEntries.filter, handler, errHandler, scheduledSubjectFilter);
    }	
})

export const request = (address, callback, errorHandler=f=>f, body, method="get") => {
    let xhr = new XMLHttpRequest();
    method = typeof body === 'object' ? "post" : "get"
    xhr.open(method, address)
    if (method === 'post') {
	xhr.setRequestHeader('Content-Type', 'application/json')
    }
    xhr.onload = () => xhr.status === 200 ? callback(xhr.responseText) : errorHandler(xhr.responseText)
    xhr.send(typeof body === 'object' ? JSON.stringify(body) : null)
}
