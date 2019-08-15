const appActions = (dispatcher) => (
    {
	onFilter: (data) => {
	    dispatcher.handleAction({type: "ON_FILTER", data});
	},
	onItemClick: (data) => {
	    dispatcher.handleAction({type: "ON_ITEM_CLICK", data})
	},
	addDateInterval: () => {
	    dispatcher.handleAction({type: "ON_ADD_DATE_INTERVAL", data: {}})
	},
	removeDateInterval: (data) => {
	    dispatcher.handleAction({type: "ON_REMOVE_DATE_INTERVAL", data})
	}
    }
)

export default appActions;
