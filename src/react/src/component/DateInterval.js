

export const DateInterval = ({className, dateInterval, handleDate=f=>f}) => (
        <div className={className}>
            <div className="labeled-dates">
                <label>begin</label>
                <input type="date"
                    value={dateInterval.value.firstValue}
                    onChange={ e=> handleDate({...dateInterval, value: {...dateInterval.value, firstValue:e.target.value}}) }/>
                <label>end</label>
                <input type="date"
                    value={dateInterval.value.lastValue}
                    onChange={ e=> handleDate({...dateInterval, value: {...dateInterval.value, lastValue:e.target.value}}) }/>
            </div>
            <button onClick={ e => { e.preventDefault(); handleDate(dateInterval, true, true); } }>X</button>
        </div>
)