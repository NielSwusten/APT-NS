import api from './api';

export const getartiestData = async () => {
  const response = await api.get('/artiest'); 
  return response.data;
};

export const updateArtiest = async (artiestId, data) => {
  const response = await api.put(`/artiest/${artiestId}`, data);
  return response.data;
};
