import React, { useEffect, useState } from 'react';
import { getalbumData, createAlbum, updateAlbum, deleteAlbum } from '../services/albumService';
import { Button, Card, Container, Row, Col, Modal, Form } from 'react-bootstrap';

const AlbumsPage = ({ token }) => {
  const [albums, setAlbums] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentAlbum, setCurrentAlbum] = useState({ id: null, artiestId: '', albumName: '', releaseDate: '' });

  useEffect(() => {
    fetchAlbums();
  }, []);

  const fetchAlbums = async () => {
    const data = await getalbumData();
    setAlbums(data);
  };

  const handleCreateOrUpdate = async () => {
    try {
      console.log("Token:", token); // Log the token to verify if it's valid
      if (currentAlbum.id) {
        // Update existing album
        await updateAlbum(currentAlbum, token);
      } else {
        // Create a new album
        await createAlbum(currentAlbum, token); // Pass the token here
      }
      fetchAlbums(); // Refresh the list of albums
      handleCloseModal(); // Close the modal after the operation
    } catch (error) {
      console.error('Error creating/updating album:', error);
      alert('Failed to create or update album. Please try again.');
    }
  };

  const handleDelete = async (id) => {
    if (!token) {
      alert('You need to be logged in to delete an album.');
      return;
    }
  
    if (window.confirm('Are you sure you want to delete this album?')) {
      try {
        await deleteAlbum(id, token); // Use the token from props
        fetchAlbums(); // Refresh the list after deletion
      } catch (error) {
        console.error('Error deleting album:', error);
        alert('Failed to delete album. Please try again.');
      }
    }
  };

  const handleCloseModal = () => {
    setShowModal(false);
    setCurrentAlbum({ id: null, artiestId: '', albumName: '', releaseDate: '' });
  };

  const handleEdit = (album) => {
    setCurrentAlbum(album);
    setShowModal(true);
  };

  return (
    <Container>
      <h1 className="my-4">Albums</h1>
      <Button variant="primary" onClick={() => setShowModal(true)}>Create Album</Button>
      <Row className="mt-4">
        {albums.map((album) => (
          <Col md={4} key={album.id} className="mb-4">
            <Card>
              <Card.Body>
                <Card.Title>{album.albumName}</Card.Title>
                <Card.Text>Artist ID: {album.artiestId}</Card.Text>
                <Card.Text>Release Date: {album.releaseDate}</Card.Text>
                <Button variant="info" onClick={() => handleEdit(album)}>Edit</Button>{' '}
                <Button variant="danger" onClick={() => handleDelete(album.id)}>Delete</Button>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* Modal for Create/Edit */}
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>{currentAlbum.id ? 'Edit Album' : 'Create Album'}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formArtistId">
              <Form.Label>Artist ID</Form.Label>
              <Form.Control
                type="text"
                value={currentAlbum.artiestId}
                onChange={(e) => setCurrentAlbum({ ...currentAlbum, artiestId: e.target.value })}
              />
            </Form.Group>
            <Form.Group controlId="formAlbumName" className="mt-3">
              <Form.Label>Album Name</Form.Label>
              <Form.Control
                type="text"
                value={currentAlbum.albumName}
                onChange={(e) => setCurrentAlbum({ ...currentAlbum, albumName: e.target.value })}
              />
            </Form.Group>
            <Form.Group controlId="formReleaseDate" className="mt-3">
              <Form.Label>Release Date</Form.Label>
              <Form.Control
                type="date"
                value={currentAlbum.releaseDate}
                onChange={(e) => setCurrentAlbum({ ...currentAlbum, releaseDate: e.target.value })}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>Cancel</Button>
          <Button variant="primary" onClick={handleCreateOrUpdate}>
            {currentAlbum.id ? 'Update' : 'Create'}
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default AlbumsPage;
