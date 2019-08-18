import styled from 'styled-components';
import { DateInterval } from '../DateInterval'

export const StyledDateInterval = styled(DateInterval)`
    border: none;
    display: flex;
    padding: 10px;
    button {
        flex: 1;
        margin: none;
    }
    .labeled-dates{
        flex: 9;
        display: grid;
        grid-template-columns: auto 1fr;
        grid-column-gap: 1em;
        label {
            font-family: 'Courier New', Courier, monospace;
            color: gray;
        }
    }

`