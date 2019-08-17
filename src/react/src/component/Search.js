export const Search = ({className, placeholder, onSearch=f=>f}) => (
    <input className={className} type="text" onInput={onSearch} placeholder={placeholder} />
)