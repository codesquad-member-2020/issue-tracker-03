import { combineReducers } from 'redux';
import issueList from './issueList';
import login from './login';
import labelList from './labelList';
import sidePanels from './sidePanels';
import milestoneList from './milestoneList';

const rootReducer = combineReducers({ issueList, login, labelList, sidePanels, milestoneList });

export default rootReducer;
