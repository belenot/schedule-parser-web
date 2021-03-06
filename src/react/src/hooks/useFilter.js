import { filterState, selectionsMap } from "../helpers";
import { useState, useEffect } from 'react'
import moment from 'moment'


export const useFilter = (initFilter, initSelections) => {
    initSelections = Object.keys(initSelections).filter( p => initSelections[p].length > 0).reduce( (ac, el) => ({...ac, [el]: initSelections[el]}), {});
    const [filter, setFilter] = useState(initFilter || filterState);
    const [selections, setSelections] = useState({studentGroups: [], teachers: [], subjects: [], classrooms: []});
    let filledSelections = {...selections, ...initSelections};
    useEffect( () => setSelections(filledSelections), 
    [filledSelections.studentGroups.length, filledSelections.teachers.length, filledSelections.subjects.length, filledSelections.classrooms.length]);
    
    const handleFilter = (type, info, data) => {
        const {dest, index} = info;
        switch(type) {
            case 'ADD':
                if (dest in selectionsMap && !filter[dest].some( el => el.value == data.value)) {
                    setFilter({...filter, [dest]: [...filter[dest], {...data}] });
                    setSelections({...selections, 
                        [selectionsMap[dest]]: selections[selectionsMap[dest]].map( s => s.id != data.value ? s : {...s, _selected: true, _excluded: data.excluded})});
                } else if (!(dest in selectionsMap)) {
                    setFilter({...filter, [dest]: [...filter[dest], {...data}] });
                }
                break;
            case 'CHANGE':
                if (index == undefined) return;
                setFilter({...filter, [dest]: filter[dest].map( (el, i) => i != index ? el : {...data})})
                break;
            case 'REMOVE':
                if (index != undefined) {
                    setFilter({...filter, [dest]: filter[dest].filter( (el, i) => i != index)})
                } else {
                    setFilter({...filter, [dest]: filter[dest].filter( (el) => el.value != data.value)})
                }
                if (dest in selectionsMap) {
                    setSelections({...selections, 
                        [selectionsMap[dest]]: selections[selectionsMap[dest]].map( selection => {
                            if (selection.id != data.value) return selection;
                             let newSelection = {...selection};
                             delete newSelection._selected;
                             delete newSelection._excluded;
                            return newSelection;
                        })
                    });
                }

                break;
        }
    }


    return [filter, selections, handleFilter];
}