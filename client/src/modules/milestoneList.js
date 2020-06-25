import * as milestoneListAPI from '$API/milestoneList';
import {
  createPromiseThunk,
  createPromiseThunkById,
  reducerUtils,
  handleAsyncActions,
  handleAsyncActionsById,
} from '$Libs/asyncUtils';

const GET_MILESTONELIST = 'milestoneList/GET_MILESTONELIST';
const GET_MILESTONELIST_SUCCESS = 'milestoneList/GET_MILESTONELIST_SUCCESS';
const GET_MILESTONELIST_ERROR = 'milestoneList/GET_MILESTONELIST_ERROR';

const GET_MILESTONE = 'milestoneList/GET_MILESTONE';
const GET_MILESTONE_SUCCESS = 'milestoneList/GET_MILESTONE_SUCCESS';
const GET_MILESTONE_ERROR = 'milestoneList/GET_MILESTONE_ERROR';

const getMilestoneReducer = handleAsyncActionsById(GET_MILESTONE, 'milestone', true);

export const getMilestoneList = createPromiseThunk(
  GET_MILESTONELIST,
  milestoneListAPI.getMilestoneList,
);
export const getMilestone = createPromiseThunkById(
  GET_MILESTONE,
  milestoneListAPI.getMilestoneById,
);

const initialState = {
  milestoneList: reducerUtils.initial(),
};

export default function milestoneList(state = initialState, action) {
  switch (action.type) {
    case GET_MILESTONELIST:
    case GET_MILESTONELIST_SUCCESS:
    case GET_MILESTONELIST_ERROR:
      return handleAsyncActions(
        GET_MILESTONELIST,
        "milestoneList",
        true
      )(state, action);
    case GET_MILESTONE:
    case GET_MILESTONE_SUCCESS:
    case GET_MILESTONE_ERROR:
      return getMilestoneReducer(state, action);
    default:
      return state;
  }
}