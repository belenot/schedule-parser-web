import React from 'react'

export const CheckboxGroup = ({className, options, display, name, handleCheck=f=>f}) => (
    <div className={className}>
        {options.map( option => 
            <label key={option}>
                {display(option)}
                <input type="checkbox" name={name} value={option} onChange={e=>handleCheck(e.target.value, false, e.target.checked)}/>
                <span></span>
            </label>
        )}
    </div>

)