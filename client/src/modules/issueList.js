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

export const getIssueList = createPromiseThunk(
  GET_ISSUELIST,
  issueListAPI.getIssueList,
);
export const resetCheckbox = () => ({ type: RESET_CHECKBOX });
export const clickCheckbox = data => ({ type: CLICK_CHECKBOX, data });

const initialState = {
  issueList: reducerUtils.initial(),
  checkbox: {
    counter: null,
    checkboxList: new Map(),
  },
};

export default function issueList(state = initialState, action) {
  switch (action.type) {
    case GET_ISSUELIST:
    case GET_ISSUELIST_SUCCESS:
      action.payload &&
        action.payload.issueResponses.forEach(data =>
          state.checkbox.checkboxList.set(data.id, false),
        );
    case GET_ISSUELIST_ERROR:
      return handleAsyncActions(
        GET_ISSUELIST,
        'issueList',
        true,
      )(state, action);
    case RESET_CHECKBOX:
      return console.log('[reset]', state.checkbox.checkboxList);
    case CLICK_CHECKBOX:
      state.checkbox.checkboxList.set(action.data.id, action.data.checked);
      return state;
    default:
      return state;
  }
}
