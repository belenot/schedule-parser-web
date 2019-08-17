import styled from 'styled-components';
import { CheckboxGroup } from '../CheckboxGroup'

export const StyledCheckboxGroup = styled(CheckboxGroup)`
    display: flex;
    flex-direction: column;
    input {
        position: relative;
        width: 1em;
        height: 1em;
        -webkit-appearance: none;
        background: #c6c6c6;
        border-radius: 2px;
        box-shadow: inset 0 0 2px rgba(0,0,0,.2);
        transition: .5s;
        :checked {
            background: darkorange;
        }
    }

`