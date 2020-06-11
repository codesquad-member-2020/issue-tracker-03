import { combineReducers } from 'redux';
import issueList from './issueList';
import checkbox from './checkbox';

const rootReducer = combineReducers({ issueList, checkbox });

export default rootReducer;
