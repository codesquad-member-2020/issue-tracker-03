const RESET_CHECKBOX = 'checkbox/RESET_CHECKBOX';
const TOGGLE_CHECKBOX = 'checkbox/TOGGLE_CHECKBOX';
const ACTIVE_CHECKBOX_ALL = 'checkbox/ACTIVE_CHECKBOX_ALL';
const DEACTIVE_CHECKBOX_ALL = 'checkbox/DEACTIVE_CHECKBOX_ALL';
const SET_CHECKBOX = 'checkbox/SET_CHECKBOX';

export const resetCheckbox = () => ({ type: RESET_CHECKBOX });
export const toggleCheckbox = id => ({
  type: TOGGLE_CHECKBOX,
  payload: id,
});
export const setCheckbox = issueList => ({
  type: SET_CHECKBOX,
  payload: issueList,
});

const initialCheckboxState = {
  totalIssueCount: null,
  checkedIssueCount: null,
  issueList: new Map(),
};

export default function checkbox(state = initialCheckboxState, action) {
  switch (action.type) {
    case RESET_CHECKBOX:
      return {
        ...state,
        initialCheckboxState,
      };
    case TOGGLE_CHECKBOX:
      state.issueList.set(action.payload, !state.issueList.get(action.payload));
      return {
        ...state,
      };
    case ACTIVE_CHECKBOX_ALL:
      break;
    case DEACTIVE_CHECKBOX_ALL:
      break;
    case SET_CHECKBOX:
      console.log('payload!!!!!!!', action.payload);
      action.payload.forEach((data, index) =>
        state.issueList.set(data.id, false),
      );
      return {
        ...state,
      };
    default:
      return state;
  }
}
