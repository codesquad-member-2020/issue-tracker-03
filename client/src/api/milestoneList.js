import axios from 'axios';

export const getMilestoneList = async filterUrl => {
  const response = await axios.get(process.env.REACT_APP_API_MILESTONELIST);
  return response.data;
};

export const getMilestoneById = async id => {
  const response = await axios.get(
    `${process.env.REACT_APP_API_MILESTONELIST}/${id}`,
  );
  return response.data;
};

export const modifyMilestone = async (id, body) => {
  const url = process.env.REACT_APP_API_MODIFY_MILESTONE + '/' + id;
  const response = await axios.put(url, body);
  return response.data;
};

export const deleteMilestone = async (id) => {
  const url = process.env.REACT_APP_API_DELETE_MILESTONE + '/' + id;
  const response = await axios.delete(url);
  return response.data;
};

export const createMilestone = async (body) => {
  const url = process.env.REACT_APP_API_CREATE_MILESTONE;
  const response = await axios.post(url, body);
  return response.data;
};