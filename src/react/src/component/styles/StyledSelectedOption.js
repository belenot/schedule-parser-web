import styled from 'styled-components';
import { SelectedOption } from '../SelectedOption';

export const StyledSelectedOption = styled(SelectedOption)`
    color: ${props => props.excluded? 'red' : 'green'};
`