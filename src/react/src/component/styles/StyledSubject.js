import styled from 'styled-components';
import Subject from '../Subject';

export const StyledSubject = styled(Subject)`
    font: 12pt;
    font-weight: bold;
    display: grid;
    grid-template-columns: 0.05fr 0.95fr;
    border-left: 4px groove lightsteelblue;
    cursor: pointer;
    *{
        cursor: pointer;
    }
    label {
        text-align: center;
    }
    .subject-info {
        margin-top: 10px;
        font: 10pt;
        font-weight: lighter;
        color: gray;
        grid-column: 1/3;
        display: flex;
        justify-content: space-around;
        /* border-bottom: 1px dotted gray;
        border-bottom: 4px double black; */
    }
    .student-groups {
        max-height: 50px;
        overflow-y: scroll;
        visibility: hidden;
        position: absolute;
        background-color: white;
        padding: 5px;
        border: 2px solid black;
        border-radius: 5px;
        box-shadow: 5px 5px 10px black;
    }
    :hover {
        .student-groups{
            visibility: visible;
        }
    }
    padding: 5px;
    margin: 5px;
`