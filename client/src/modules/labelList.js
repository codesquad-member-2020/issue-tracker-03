import * as labelListAPI from '$API/labelList';
import {
  createPromiseThunk,
  reducerUtils,
  handleAsyncActions,
} from '$Libs/asyncUtils';

const GET_LABELLIST = 'labelList/GET_LABELLIST';
const GET_LABELLIST_SUCCESS = 'labelList/GET_LABELLIST_SUCCESS';
const GET_LABELLIST_ERROR = 'labelList/GET_LABELLIST_ERROR';

export const getLabelList = createPromiseThunk(
  GET_LABELLIST,
  labelListAPI.getLabelList,
);

const initialState = {
  labelList: reducerUtils.initial(),
};

export default function labelList(state = initialState, action) {
  switch (action.type) {
    case GET_LABELLIST:
    case GET_LABELLIST_SUCCESS:
    case GET_LABELLIST_ERROR:
      return handleAsyncActions(
        GET_LABELLIST,
        "labelList",
        true
      )(state, action);
    default:
      return state;
  }
}