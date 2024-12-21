import api from './api';

export const getalbumData = async () => {
  const response = await api.get('/album'); 
  return response.data;
};


export const updateAlbum = async (albumData, token) => {
  const response = await fetch(`http://localhost:8080/album/${albumData.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${token}`, // Add the token here
    },
    body: JSON.stringify(albumData),
  });
  if (!response.ok) throw new Error('Failed to update album');
  return response.json();
};

export const createAlbum = async (albumData, token) => {
  try {
    const response = await fetch('http://localhost:8080/album', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${token}`, // Add the token here
      },
      body: JSON.stringify(albumData),
    });

    if (!response.ok) {
      throw new Error('Failed to create album');
    }

    return await response.json();
  } catch (error) {
    console.error('Error creating album:', error);
    throw error;
  }
};




export const deleteAlbum = async (id, token) => {
  try {
      const response = await fetch(`http://localhost:8080/album/${id}`, {
          method: 'DELETE',
          headers: {
              Authorization: `Bearer ${token}`,
          },
      });

      if (!response.ok) {
          const errorDetails = await response.text();
          console.error('Error response from server:', errorDetails);
          throw new Error(`Failed to delete album. Server responded with: ${response.status}`);
      }
  } catch (error) {
      console.error('Error deleting album:', error.message);
      throw error;
  }
};

