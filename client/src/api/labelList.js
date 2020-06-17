import axios from 'axios';

export const getLabelList = async filterUrl => {
  const response = await axios.get(process.env.REACT_APP_API_LABELLIST);

  return response.data;
};
