import styled from 'styled-components';
import ControlPanel from '../ControlPanel';

export const StyledControlPanel = styled(ControlPanel)`
    width: 20%;
    background-color: inherit;
    border: 4px groove silver;
    border-radius: 10px 10px 10px 10px;
    box-shadow: inset 0 0 10px gray;
    padding: 20px;

    *{
        outline: none;
    }
    *::-webkit-scrollbar {
        width: 2px;
        height: 2px;
        background-color: lightcoral;
    }
    *::-webkit-scrollbar-thumb {
        background-color: black;
    }
    button {
        background: none;
        border:none;
        transition: .3s;
        :hover {
            background-color: white;
            box-shadow: 0 0 2px 1px darkorange;
            color: darkorange;
        }
    }
    .filter-btn {
        margin-top: 20px;
        font: 12pt black;
        font-family: Arial, Helvetica, sans-serif;
        font-weight: bold;
    }
    .checkbox-groups {
        display: flex;
        justify-content: space-around;
    }
    .date-intervals{
        max-height: 200px;
        overflow-y: scroll;
        overflow-x: hidden;
    }
`