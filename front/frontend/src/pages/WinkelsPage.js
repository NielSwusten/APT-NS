import React, { useEffect, useState } from 'react';
import { getwinkelData } from '../services/winkelService';
import { Card, Container, Row, Col } from 'react-bootstrap';

const WinkelsPage = () => {
  const [winkels, setWinkels] = useState([]);

  useEffect(() => {
    const fetchWinkels = async () => {
      const data = await getwinkelData();
      setWinkels(data);
    };
    fetchWinkels();
  }, []);

  return (
    <Container>
      <h1 className="my-4">Winkels</h1>
      <Row>
        {winkels.map((winkel) => (
          <Col md={4} key={winkel.id} className="mb-4">
            <Card>
              <Card.Body>
                <Card.Title>{winkel.name}</Card.Title>
                <Card.Text>ID: {winkel.id}</Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>
    </Container>
  );
};

export default WinkelsPage;
