import axios from 'axios';

export const getIssueList = async (filterUrl) => {
  //const url = process.env.REACT_APP_API_ISSUELIST;
  const response = await axios.get("https://run.mocky.io/v3/446bf85d-f024-4f43-88ce-1930ce4c2fa5");
  return response.data;
};
