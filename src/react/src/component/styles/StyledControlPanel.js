import styled from 'styled-components';
import ControlPanel from '../ControlPanel';

export const StyledControlPanel = styled(ControlPanel)`
    width: 20%;
    background-color: white;
    border: 4px groove silver;
    border-radius: 10px 10px 10px 10px;
    box-shadow: inset 0 0 10px gray;
    padding: 20px;
    position: fixed;
    transform: translate(${props => props.hidden ? '-30vw' : '0'}, 0);
    transition: .5s;

    *{
        outline: none;
    }
    *::-webkit-scrollbar {
        width: 2px;
        height: 2px;
        background-color: lightsteelblue;
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
    .hide-btn {
        transition: .5s;
        transform: translate(${props => props.hidden ? '25vw' : '0'}, 0);
        border-radius: ${props => props.hidden?'0 20px 20px 0' : '20px 0 0 20px'};
        width: 4em;
        height: 1em;
        background: black;
        color: white;
        border-bottom: lightsteelblue;
        :hover {
            background: repeating-linear-gradient(-45deg, darkorange, darkorange 1px, white 1px, white 5px);
        }
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