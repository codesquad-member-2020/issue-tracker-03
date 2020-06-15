import { combineReducers } from 'redux';
import issueList from './issueList';
import login from './login';

const rootReducer = combineReducers({ issueList, login });

export default rootReducer;
