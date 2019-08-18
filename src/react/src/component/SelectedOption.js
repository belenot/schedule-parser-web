export const SelectedOption = ({className, value, onRemove}) => (
    <div className={className}>
        <label>{value}</label>
        <button onClick={e => { e.preventDefault(); onRemove()}}>X</button>
    </div>
)