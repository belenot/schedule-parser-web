import styled from 'styled-components';

import { Select } from '../Select'

export const StyledSelect = styled(Select)`
    width: 100%;
    margin: 20px 0;
    .searched-items {
        overflow: scroll;
        overflow-x: hidden;
        max-height: 200px;
        box-sizing: border-box;
        margin-top: 10px;
    }


`