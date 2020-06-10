import axios from 'axios';

const GET_ISSUES = 'issues/GET_ISSUES';
const GET_ISSUES_SUCCESS = 'issues/GET_ISSUES_SUCCESS';
const GET_ISSUES_ERROR = 'issues/GET_ISSUES_ERROR';

const GET_ISSUE = 'issues/GET_ISSUE';
const GET_ISSUE_SUCCESS = 'issues/GET_ISSUE_SUCCESS';
const GET_ISSUE_ERROR = 'issues/GET_ISSUE_ERROR';

export const getIssues = () => async dispatch => {
  dispatch({ type: GET_ISSUES });
  try {
    const response = await axios.get('http://localhost:4000/issueResponses');
    const data = response.data;
    dispatch({ type: GET_ISSUES_SUCCESS, data });
  } catch (e) {
    dispatch({ type: GET_ISSUES_ERROR, error: e });
  }
};

const initialState = {
  issues: {
    loading: false,
    data: null,
    error: null,
  },
  issue: {
    loading: false,
    data: null,
    error: null,
  },
};

export default function issues(state = initialState, action) {
  switch (action.type) {
    case GET_ISSUES:
      return {
        ...state,
        issues: {
          loading: true,
          data: null,
          error: null,
        },
      };
    case GET_ISSUES_SUCCESS:
      return {
        ...state,
        issues: {
          loading: false,
          data: action.data,
          error: null,
        },
      };
    case GET_ISSUES_ERROR:
      return {
        ...state,
        issues: {
          loading: false,
          data: null,
          error: action.error,
        },
      };
    default:
      return state;
  }
}
