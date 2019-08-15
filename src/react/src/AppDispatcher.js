import { Dispatcher } from 'flux'

class AppDispatcher extends Dispatcher {
    handleAction(action) {
	this.dispatch({
	    source: 'VIEW',
	    action
	})
    }
    handleApi(action) {
	this.dispatch({
	    source: 'API',
	    action
	})
    }
}

export default AppDispatcher
