import axios from 'axios';

export const getLabelList = async filterUrl => {
  const response = await axios.get(process.env.REACT_APP_API_LABELLIST);
  console.log(response.data);
  return response.data;
};

export const modifyLabel = async (id, body) => {
  const url = process.env.REACT_APP_API_MODIFY_LABEL + '/' + id;
  const response = await axios.put(url, body);
  return response.data;
};

export const deleteLabel = async (id) => {
  const url = process.env.REACT_APP_API_DELETE_LABEL + '/' + id;
  const response = await axios.delete(url);
  return response.data;
};

export const createLabel = async (body) => {
  const url = process.env.REACT_APP_API_CREATE_LABEL;
  const response = await axios.post(url, body);
  return response.data;
};
