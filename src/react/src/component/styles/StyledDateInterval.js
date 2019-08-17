import styled from 'styled-components';
import { DateInterval } from '../DateInterval'

export const StyledDateInterval = styled(DateInterval)`
    border: none;
    border-right: 2px solid lightcoral;
    display: grid;
    grid-template-rows: 1fr 1fr;
    grid-template-columns: 10fr 1fr;
    padding: 10px;
    button {
        grid-row: 1 / 3;
        grid-column: 2;
        margin: none;
    }

`