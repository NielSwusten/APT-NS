import React, { useEffect, useState } from 'react';
import { getalbumData, createAlbum } from '../services/albumService';
import { getwinkelData } from '../services/winkelService';
import { getartiestData } from '../services/artiestService';

const Dashboard = ({ token }) => {
  const [albums, setAlbums] = useState([]);
  const [artists, setArtists] = useState([]);
  const [winkels, setWinkels] = useState([]);
  const [newAlbum, setNewAlbum] = useState({ artiestId: '', albumName: '', releaseDate: '' });

  useEffect(() => {
    const fetchData = async () => {
      try {
        const albumdata = await getalbumData();
        setAlbums(albumdata);
        const artistdata = await getartiestData();
        setArtists(artistdata);
        const winkeldata = await getwinkelData();
        setWinkels(winkeldata);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  const handleCreateAlbum = async (e) => {
    e.preventDefault();
    try {
      const createdAlbum = await createAlbum(newAlbum, token); // Pass the token here
      setAlbums((prev) => [...prev, createdAlbum]); // Add the new album to the list
      setNewAlbum({ artiestId: '', albumName: '', releaseDate: '' }); // Reset the form
    } catch (error) {
      console.error('Error creating album:', error);
    }
  };

  return (
    <>
      <div>
        <h1>Dashboard</h1>
      </div>

      <div>
        <h1>Albums</h1>
        <ul>
          {albums.map((album) => (
            <li key={album.id}>{album.albumName}</li>
          ))}
        </ul>
      </div>

      <div>
        <h1>Create Album</h1>
        <form onSubmit={handleCreateAlbum}>
          <div>
            <label>Artist ID:</label>
            <input
              type="text"
              value={newAlbum.artiestId}
              onChange={(e) => setNewAlbum({ ...newAlbum, artiestId: e.target.value })}
              required
            />
          </div>
          <div>
            <label>Album Name:</label>
            <input
              type="text"
              value={newAlbum.albumName}
              onChange={(e) => setNewAlbum({ ...newAlbum, albumName: e.target.value })}
              required
            />
          </div>
          <div>
            <label>Release Date:</label>
            <input
              type="date"
              value={newAlbum.releaseDate}
              onChange={(e) => setNewAlbum({ ...newAlbum, releaseDate: e.target.value })}
              required
            />
          </div>
          <button type="submit">Create Album</button>
        </form>
      </div>

      <div>
        <h1>Artists</h1>
        <ul>
          {artists.map((artist) => (
            <li key={artist.id}>{artist.name}</li>
          ))}
        </ul>
      </div>

      <div>
        <h1>Shops</h1>
        <ul>
          {winkels.map((winkel) => (
            <li key={winkel.id}>{winkel.name}</li>
          ))}
        </ul>
      </div>
    </>
  );
};

export default Dashboard;
