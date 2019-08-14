import { Dispatcher } from 'flux'

class AppDispatcher extends Dispatcher {
    handleAction(action) {
	this.dispatch({
	    source: 'VIEW_ACTION',
	    action
	})
    }
}

export default AppDispatcher
