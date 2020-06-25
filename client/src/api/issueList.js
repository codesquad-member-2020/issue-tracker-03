import axios from 'axios';

export const getIssueList = async filterUrl => {
  console.log("!!!!!!!!", filterUrl);
  const response = await axios.get(process.env.REACT_APP_API_ISSUELIST + filterUrl);
  return response.data;
};

export const getIssueById = async id => {
  const response = await axios.get(
    `${process.env.REACT_APP_API_ISSUELIST}/${id}`,
  );
  return response.data;
};
