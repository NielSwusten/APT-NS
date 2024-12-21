import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import AlbumsPage from './pages/AlbumsPage';
import WinkelsPage from './pages/WinkelsPage';
import ArtiestenPage from './pages/ArtiestenPage';

const App = () => {
  const [userToken, setUserToken] = useState(null);

  return (
    <GoogleOAuthProvider clientId="390971080892-aq334h72jejri8gl34jt6qaalrr9497i.apps.googleusercontent.com">
      <Router>
        <div className="container">
          <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <Link className="navbar-brand" to="/">My App</Link>
            <div className="collapse navbar-collapse">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link className="nav-link" to="/">Albums</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/winkels">Winkels</Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to="/artiesten">Artiesten</Link>
                </li>
              </ul>
              {!userToken && (
                <GoogleLogin
                  onSuccess={(credentialResponse) => setUserToken(credentialResponse.credential)}
                  onError={() => console.error('Login Failed')}
                />
              )}
            </div>
          </nav>
          <Routes>
            <Route path="/" element={<AlbumsPage token={userToken} />} />
            <Route path="/winkels" element={<WinkelsPage />} />
            <Route path="/artiesten" element={<ArtiestenPage />} />

          </Routes>
        </div>
      </Router>
    </GoogleOAuthProvider>
  );
};

export default App;
