import axios from 'axios';
import { createPromiseThunk, reducerUtils, handleAsyncActions } from '$Libs/asyncUtils';

const getAsyncAssignees = async type => {
  const response = await axios.get(`http://15.164.138.255/api/assignees`);
  return response.data;
};
const getAsyncLabels = async type => {
  const response = await axios.get(`http://15.164.138.255/api/labels`);
  return response.data;
};
const getAsyncMilestones = async type => {
  const response = await axios.get(`http://15.164.138.255/api/milestones`);
  return response.data;
};

const GET_ASSIGNEES = 'sidePanels/GET_ASSIGNEES';
const GET_ASSIGNEES_SUCCESS = 'sidePanels/GET_ASSIGNEES_SUCCESS';
const GET_ASSIGNEES_ERROR = 'sidePanels/GET_ASSIGNEES_ERROR';
const GET_LABELS = 'sidePanels/GET_LABELS';
const GET_LABELS_SUCCESS = 'sidePanels/GET_LABELS_SUCCESS';
const GET_LABELS_ERROR = 'sidePanels/GET_LABELS_ERROR';
const GET_MILESTONES = 'sidePanels/GET_MILESTONES';
const GET_MILESTONES_SUCCESS = 'sidePanels/GET_MILESTONES_SUCCESS';
const GET_MILESTONES_ERROR = 'sidePanels/GET_MILESTONES_ERROR';

export const getAssignees = createPromiseThunk(GET_ASSIGNEES, getAsyncAssignees);
export const getLabels = createPromiseThunk(GET_LABELS, getAsyncLabels);
export const getMilestones = createPromiseThunk(GET_MILESTONES, getAsyncMilestones);

const initialState = {
  assignees: reducerUtils.initial(),
  labels: reducerUtils.initial(),
  milestones: reducerUtils.initial(),
};

export default function sidePanels(state = initialState, action) {
  switch (action.type) {
    case GET_ASSIGNEES:
    case GET_ASSIGNEES_SUCCESS:
    case GET_ASSIGNEES_ERROR:
      return handleAsyncActions(GET_ASSIGNEES, 'assignees', true)(state, action);

    case GET_LABELS:
    case GET_LABELS_SUCCESS:
    case GET_LABELS_ERROR:
      return handleAsyncActions(GET_LABELS, 'labels', true)(state, action);
    case GET_MILESTONES:
    case GET_MILESTONES_SUCCESS:
    case GET_MILESTONES_ERROR:
      return handleAsyncActions(GET_MILESTONES, 'milestones', true)(state, action);
    default:
      return state;
  }
}
