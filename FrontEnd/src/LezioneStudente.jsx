import React from 'react';
import Lezioni from './Lezioni';

export default function LezioniStudente() {
  return <Lezioni endpoint="/api/lezioni/oraristudente" title="Le tue lezioni (Studente)" />;
}