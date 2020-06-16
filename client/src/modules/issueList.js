import * as issueListAPI from '$API/issueList';
import {
  createPromiseThunk,
  createPromiseThunkById,
  reducerUtils,
  handleAsyncActions,
  handleAsyncActionsById,
} from '$Libs/asyncUtils';

const GET_ISSUELIST = 'issueList/GET_ISSUELIST';
const GET_ISSUELIST_SUCCESS = 'issueList/GET_ISSUELIST_SUCCESS';
const GET_ISSUELIST_ERROR = 'issueList/GET_ISSUELIST_ERROR';

const GET_ISSUE = 'issueList/GET_ISSUE';
const GET_ISSUE_SUCCESS = 'issueList/GET_ISSUE_SUCCESS';
const GET_ISSUE_ERROR = 'issueList/GET_ISSUE_ERROR';

const RESET_CHECKBOX = 'issueList/RESET_CHECKBOX';
const CLICK_CHECKBOX = 'issueList/CLICK_CHECKBOX';
const ALL_CHECKBOX = 'issueList/ALL_CHECKBOX';

export const getIssueList = createPromiseThunk(
  GET_ISSUELIST,
  issueListAPI.getIssueList,
);
export const getIssue = createPromiseThunkById(
  GET_ISSUE,
  issueListAPI.getIssueById,
);

export const resetCheckbox = () => ({ type: RESET_CHECKBOX });
export const clickCheckbox = data => ({ type: CLICK_CHECKBOX, data });
export const allCheckbox = () => ({ type: ALL_CHECKBOX });

const getIssueReducer = handleAsyncActionsById(GET_ISSUE, 'issue', true);

const initialState = {
  issueList: reducerUtils.initial(),
  issue: {},
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
        'issueList',
        true,
      )(state, action);

      state.checkbox.totalCount = data.issueList.data.response.length;
      data.issueList.data.response.forEach(data =>
        state.checkbox.checkboxList.set(data.id, false),
      );

      return data;
    case GET_ISSUELIST:
    case GET_ISSUELIST_ERROR:
      return handleAsyncActions(
        GET_ISSUELIST,
        'issueList',
        true,
      )(state, action);
    case GET_ISSUE:
    case GET_ISSUE_SUCCESS:
    case GET_ISSUE_ERROR:
      return getIssueReducer(state, action);

    case RESET_CHECKBOX:
      const resetCount = 0;
      state.checkbox.checkedCount = resetCount;

      state.issueList.data.response.forEach(data => {
        state.checkbox.checkboxList.set(data.id, false);
      });

      return {
        ...state,
      };
    case CLICK_CHECKBOX:
      const adjustedCount = 1;
      action.data.checked
        ? (state.checkbox.checkedCount += adjustedCount)
        : (state.checkbox.checkedCount -= adjustedCount);

      state.checkbox.checkboxList.set(action.data.id, action.data.checked);

      return {
        ...state,
      };
    case ALL_CHECKBOX:
      state.checkbox.checkedCount = state.checkbox.totalCount;
      state.issueList.data.response.forEach(data => {
        state.checkbox.checkboxList.set(data.id, true);
      });

      return {
        ...state,
      };
    default:
      return state;
  }
}
