

export const DateInterval = ({className, dateInterval, handleDate=f=>f}) => (
        <div className={className}>
            <input type="date"
                value={dateInterval.value.firstValue}
                onChange={ e=> handleDate('FIRST_DATE_INTERVAL', e.target.value) }/>
            <input type="date"
                value={dateInterval.value.lastValue}
                onChange={ e=> handleDate('LAST_DATE_INTERVAL', e.target.value) }/>
            <button onClick={ e => { e.preventDefault(); handleDate('REMOVE_DATE_INTERVAL', true); } }>X</button>
        </div>
)