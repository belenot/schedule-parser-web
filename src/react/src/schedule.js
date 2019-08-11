import React from 'react'
import ReactDOM  from 'react-dom'

import Schedule from './component/Schedule'

window.React = React;

const loadSchedule = () => {
    let xhr = new XMLHttpRequest()
    xhr.open("get", "/schedule/api/"+encodeURIComponent("БАСО-02-16"))
    xhr.onload =() => {
	if (xhr.status === 200) {
	    rerender(JSON.parse(xhr.responseText));
	} else {
	    alert("Error! Can't get schedule from server");
	}
    }
    xhr.send();
}

var rerender = (schedule) => {
    ReactDOM.render(
	<Schedule {...schedule} />,
	document.querySelector('#app')
    )
}

window.onload = () => loadSchedule()

