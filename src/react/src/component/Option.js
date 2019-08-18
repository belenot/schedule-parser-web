export const Option = ({className, value, onSelect}) => {
    return (
        <div className={className}>
            <label>{value}</label>
            <div>
                <button onClick={ e=> {e.preventDefault(); onSelect(false)}}>+</button>
                <button onClick={ e=> {e.preventDefault(); onSelect(true)}}>-</button>
            </div>
        </div>
        
    )
}