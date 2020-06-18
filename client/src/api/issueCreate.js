import axios from 'axios';

export const createIssue = async body => {
  const response = await axios.post(process.env.REACT_APP_API_CREATE_ISSUE, body);
  return response.data;
};
