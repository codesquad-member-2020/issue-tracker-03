import axios from 'axios';

export const getIssueList = async filterUrl => {
  const response = await axios.get(process.env.REACT_APP_API_ISSUELIST);
  return response.data;
};
