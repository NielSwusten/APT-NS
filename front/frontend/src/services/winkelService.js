import api from './api';

export const getwinkelData = async () => {
  const response = await api.get('/winkel'); 
  return response.data;
};

export const updateWinkel = async (winkelId, data) => {
  const response = await api.put(`/winkel/${winkelId}`, data);
  return response.data;
};
