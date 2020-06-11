import * as issueListAPI from '$API/issueList';
import {
  createPromiseThunk,
  reducerUtils,
  handleAsyncActions,
} from '$Libs/asyncUtils';

const GET_ISSUELIST = 'issueList/GET_ISSUELIST';
const GET_ISSUELIST_SUCCESS = 'issueList/GET_ISSUELIST_SUCCESS';
const GET_ISSUELIST_ERROR = 'issueList/GET_ISSUELIST_ERROR';

export const getIssueList = createPromiseThunk(GET_ISSUELIST, issueListAPI.getIssueList);

const initialState = {
  issueList: reducerUtils.initial(),
};

export default function issueList(state = initialState, action) {
  switch (action.type) {
    case GET_ISSUELIST:
    case GET_ISSUELIST_SUCCESS:
    case GET_ISSUELIST_ERROR:
      return handleAsyncActions(GET_ISSUELIST, 'issueList', true)(state, action);
    default:
      return state;
  }
}
