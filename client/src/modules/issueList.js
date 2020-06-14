import * as issueListAPI from '$API/issueList';
import {
  createPromiseThunk,
  reducerUtils,
  handleAsyncActions,
} from '$Libs/asyncUtils';

const GET_ISSUELIST = 'issueList/GET_ISSUELIST';
const GET_ISSUELIST_SUCCESS = 'issueList/GET_ISSUELIST_SUCCESS';
const GET_ISSUELIST_ERROR = 'issueList/GET_ISSUELIST_ERROR';

const RESET_CHECKBOX = 'issueList/RESET_CHECKBOX';
const CLICK_CHECKBOX = 'issueList/CLICK_CHECKBOX';
const ALL_CHECKBOX = 'issueList/ALL_CHECKBOX';

export const getIssueList = createPromiseThunk(
  GET_ISSUELIST,
  issueListAPI.getIssueList,
);
export const resetCheckbox = () => ({ type: RESET_CHECKBOX });
export const clickCheckbox = data => ({ type: CLICK_CHECKBOX, data });
export const allCheckbox = () => ({ type: ALL_CHECKBOX });

const initialState = {
  issueList: reducerUtils.initial(),
  checkbox: {
    totalCount: 0,
    checkedCount: 0,
    checkboxList: new Map(),
  },
};

export default function issueList(state = initialState, action) {
  switch (action.type) {
    case GET_ISSUELIST_SUCCESS:
      const data = handleAsyncActions(
        GET_ISSUELIST,
        "issueList",
        true
      )(state, action);

      const increasementCount = 1;
      data.issueList.data.response.issueResponses.forEach((data) => {
        state.checkbox.checkboxList.set(data.id, false);
        state.checkbox.totalCount += increasementCount;
      });

      return data;
    case GET_ISSUELIST:
    case GET_ISSUELIST_ERROR:
      return handleAsyncActions(
        GET_ISSUELIST,
        "issueList",
        true
      )(state, action);
    case RESET_CHECKBOX:
      const resetCount = 0;
      state.checkbox.checkedCount = resetCount;

      state.issueList.data.response.issueResponses.forEach((data) => {
        state.checkbox.checkboxList.set(data.id, false);
      });
      
      return {
        ...state,
      }
    case CLICK_CHECKBOX:
      const adjustedCount = 1;
      action.data.checked
        ? (state.checkbox.checkedCount += adjustedCount)
        : (state.checkbox.checkedCount -= adjustedCount);

      state.checkbox.checkboxList.set(action.data.id, action.data.checked);

      return {
        ...state,
      }
    case ALL_CHECKBOX:
      state.checkbox.checkedCount = state.checkbox.totalCount;
      state.issueList.data.response.issueResponses.forEach((data) => {
        state.checkbox.checkboxList.set(data.id, true);
      });

      return {
        ...state,
      }
    default:
      return state;
  }
}
