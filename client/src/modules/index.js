import { combineReducers } from 'redux';
import issueList from './issueList';
import login from './login';
import labelList from './labelList';

const rootReducer = combineReducers({ issueList, login, labelList });

export default rootReducer;
