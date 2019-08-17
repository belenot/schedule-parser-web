import { StyledOption } from './styles/StyledOption'
import { StyledSearch } from './styles/StyledSearch'
import { useState } from 'react'

export const Select = ({className, options, display, placeholder, handleSelect=f=>f}) => {
    const [searched, setSearched] = useState(new RegExp("^$"));
    const [showAll, setShowAll] = useState(false);
    const onSelect = (id, excluded) => handleSelect(id, excluded)
    
    const onSearch = e => {
        e.preventDefault();
        let searched = RegExp(e.target.value.toLowerCase() || "^$");
        setSearched(searched);
    }

    const onShowAll = () => {
        setShowAll(!showAll); 
    }

    return (
        <div multiple className={className}>
            <StyledSearch onSearch={onSearch} placeholder={placeholder} />
            <div className="searched-items">
                {options.filter(option => searched.test(String(display(option)).toLowerCase()))
                .map( option => 
                    <StyledOption id={option.id || display(option)}
                        key={option.id || display(option)}
                        value={display(option)} onSelect={onSelect}/>
                    )}
            </div>
        </div>
    )
}