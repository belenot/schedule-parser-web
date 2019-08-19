import styled from 'styled-components';
import Day from '../Day';

export const StyledDay = styled(Day)`
    display: flex;
    flex-direction: column;
    
    margin: 10px;
    padding: 15px;
    border: 4px groove lightsteelblue;
    border-radius: 7px;

    .date {
        color: gray;
        font-size: 14pt;
        font-weight: bold;
        font-family: 'Courier New', Courier, monospace;
        margin-left: auto;
        margin-right: auto;
    }

`