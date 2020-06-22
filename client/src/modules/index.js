import { combineReducers } from 'redux';
import issueList from './issueList';
import login from './login';
import labelList from './labelList';
import sidePanels from './sidePanels';

const rootReducer = combineReducers({ issueList, login, labelList, sidePanels });

export default rootReducer;
