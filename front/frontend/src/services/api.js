import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080', // Gateway URL
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add interceptors for authentication, if needed
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('token'); // Adjust based on your auth method
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
