import styled from 'styled-components';
import { Option } from '../Option'

export const StyledOption = styled(Option)`
    width: 90%;
    display: flex;
    justify-content: space-between;
    background-color: hsl(220, 50%, 95%);
    border: 1px solid gray;
    padding: 5px;
    margin: 10px;
    button {
        background: none;
        border: none;
        margin: 5px;
    }
    div {
        display: flex;
        flex-direction: row;
    }
    label {
        color: gray;
        font-family: 'Courier New', Courier, monospace;
        overflow: hidden;
    }
    
`