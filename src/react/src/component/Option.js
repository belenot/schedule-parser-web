export const Option = ({className, id, value, onSelect}) => {
    return (
        <div className={className} value={id}>
            <label>{value}</label>
            <div>
                <button onClick={ e=> {e.preventDefault(); onSelect(id || value, false)}}>+</button>
                <button onClick={ e=> {e.preventDefault(); onSelect(id || value, true)}}>-</button>
            </div>
        </div>
        
    )
}