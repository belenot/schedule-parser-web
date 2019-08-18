import { StyledOption } from './styles/StyledOption'
import { StyledSearch } from './styles/StyledSearch'
import { useState } from 'react'
import { StyledSelectedOption } from './styles/StyledSelectedOption';

export const Select = ({className, options=[], selectedOptions=[], display, placeholder, handleSelect=f=>f}) => {
    const [searched, setSearched] = useState(RegExp("^$"));
    const onSelect = (id, excluded) => handleSelect('ADD', id, excluded);
    const onRemove = (id) => handleSelect('REMOVE', id);
    
    const onSearch = e => {
        e.preventDefault();
        let searched = RegExp(e.target.value.toLowerCase() || "^$");
        setSearched(searched);
    }

    return (
        <div multiple className={className}>
            <StyledSearch onSearch={onSearch} placeholder={placeholder} />
            <div className="searched-items">
                {options.filter(option => searched.test(String(display(option)).toLowerCase()) && !option._selected)
                .map( option => 
                    <StyledOption 
                        key={option.id}
                        value={display(option)} onSelect={ excluded => onSelect(option.id, excluded)}/>
                    )}
            </div>
            <div className="selected-items">
                {options.filter(option => option._selected).map(option => 
                    <StyledSelectedOption key={option.id} value={display(option)} excluded={option._excluded} onRemove={() => onRemove(option.id)} />
                )}
            </div>
        </div>
    )
}