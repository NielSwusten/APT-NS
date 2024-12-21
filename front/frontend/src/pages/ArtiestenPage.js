import React, { useEffect, useState } from 'react';
import { getartiestData } from '../services/artiestService';
import { Card, Container, Row, Col } from 'react-bootstrap';

const ArtiestenPage = () => {
  const [artists, setArtists] = useState([]);

  useEffect(() => {
    const fetchArtists = async () => {
      const data = await getartiestData();
      setArtists(data);
    };
    fetchArtists();
  }, []);

  return (
    <Container>
      <h1 className="my-4">Artiesten</h1>
      <Row>
        {artists.map((artist) => (
          <Col md={4} key={artist.id} className="mb-4">
            <Card>
              <Card.Body>
                <Card.Title>{artist.name}</Card.Title>
                <Card.Text>{artist.description}</Card.Text>
                <Card.Text>Born: {artist.dateOfBirth}</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default ArtiestenPage;
