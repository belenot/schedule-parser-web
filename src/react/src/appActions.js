const appActions = (dispatcher) => (
    {
	clickComponent: (data) => {
	    dispatcher.handleAction({type: "CLICK_COMPONENT", data});
	},
	filterSchedule: (data) => {
	    dispatcher.handleAction({type: "FILTER_SCHEDULE", data});
	},
	loadSchedule: (data) => {
	    dispatcher.handleAction({type:"LOAD_SCHEDULE", data});
	}
    }
)

export default appActions;
