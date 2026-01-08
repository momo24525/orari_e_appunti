import React from 'react';
import Lezioni from './Lezioni';

export default function LezioniProfessore() {
  return <Lezioni endpoint="/api/lezioni/orariprofessore" title="Le tue lezioni (Professore)" />;
}