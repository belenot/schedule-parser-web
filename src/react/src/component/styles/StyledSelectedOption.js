import styled from 'styled-components';
import { SelectedOption } from '../SelectedOption';

export const StyledSelectedOption = styled(SelectedOption)`
    background-color: ${props => props.excluded? '#f0808024' : '#00ff0024'};
    color: gray;
    padding: 2px 5px;
    font-size: 10pt;
    border: 1px solid gray;
    border-radius: 20px;
    width: fit-content;
    margin: 2px;
    button {
        border-radius: 20px;
    }

`