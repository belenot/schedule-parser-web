export const lessonTimes = ['L1', 'L2', 'L3', 'L4', 'L5', 'L6'];
export const lessonTypes = ['LECTION', 'PRACTICE', 'LAB'];
export const displayedLessonTime = {
    'L1': '1',
    'L2': '2',
    'L3': '3',
    'L4': '4',
    'L5': '5',
    'L6': '6'
}
export const displayedLessonType = {
    'LECTION': 'Лекция',
    'PRACTICE': 'Практика',
    'LAB': 'Лаб.работа'
}

export const filterState = () => ({
    studentGroupsIds: [],
    teachersIds: [],
    subjectsIds: [],
    classroomsIds: [],
    lessonTypes: [],
    lessonTimes: [],
    dateIntervals: []
})

export const curDateInterval = () => {
    let date = moment().format('YYYY-MM-DD');
    return {
        value: {
            firstValue: date,
            lastValue: date
        }, 
        excluded: false
    }
}
export const selectionsMap = {
    studentGroupsIds: 'studentGroups', 
    teachersIds: 'teachers', 
    subjectsIds: 'subjects', 
    classroomsIds:'classrooms'
};
export const randomColor = () => {
    let randomHue = Math.ceil(Math.random() * 360);
    return `hsl(${randomHue}, 50%, 70%)`
}