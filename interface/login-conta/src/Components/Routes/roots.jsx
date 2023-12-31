import React from 'react';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import Index from '../Home/index';
import { LoginCriar } from '../LoginCriar/LoginCriar'

const roots = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginCriar />} />
        <Route path="/index" element={<Index />} />
      </Routes>
    </Router>
  );
};

export default roots;

