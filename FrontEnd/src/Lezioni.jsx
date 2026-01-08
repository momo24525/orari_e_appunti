import React, { useEffect, useState, useContext } from 'react';
import AuthContext from './AuthContext';
import 'bootstrap/dist/css/bootstrap.min.css';


export default function Lezioni() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [lezioni, setLezioni] = useState([]);
  const { role } = useContext(AuthContext); // Usa SOLO il context

  const ORE_SCOLASTICHE = [
    { key: "H08_09", label: "1ª ora", orario: "08:00 - 09:00" },
    { key: "H09_10", label: "2ª ora", orario: "09:00 - 10:00" },
    { key: "H10_11", label: "3ª ora", orario: "10:00 - 11:00" },
    { key: "H11_12", label: "4ª ora", orario: "11:00 - 12:00" },
    { key: "H12_13", label: "5ª ora", orario: "12:00 - 13:00" },
    { key: "H13_14", label: "6ª ora", orario: "13:00 - 14:00" },
    { key: "H14_15", label: "7ª ora", orario: "14:00 - 15:00" }
  ];

  const oggi = new Date();
  const dataOggi = oggi.toLocaleDateString('it-IT', {
    weekday: 'long',
    day: '2-digit',
    month: 'long',
    year: 'numeric'
  });

  const giornoOggiEnum = new Date()
    .toLocaleDateString('it-IT', { weekday: 'long' })
    .toUpperCase()
    .normalize("NFD")
    .replace(/[\u0300-\u036f]/g, '');

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (!token) {
      setError("Non autenticato");
      setLoading(false);
      return;
    }

    if (role === null) {
      return; // Aspetta che il context decodifichi il role → l'effect ripartirà da solo
    }

    // Usa il role dal context
    const endpoint = role === 'professore'
      ? 'http://localhost:8080/api/lezioni/orariprofessore'
      : 'http://localhost:8080/api/lezioni/oraristudente';

    console.log("Endpoint chiamato:", endpoint); // DEBUG

    fetch(endpoint, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
      .then(res => {
        if (!res.ok) throw new Error(res.statusText || 'Errore nella risposta');
        return res.json();
      })
      .then(data => setLezioni(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false));
  }, [role]); // Dipendenza: role

  if (loading) return <div className="text-center p-4">Caricamento...</div>;
  if (error) return <div className="text-red-600 p-4">Errore: {error}</div>;



  if (!lezioni.length) {
    return (
      <div className="text-center p-4">
        <h1 className="text-2xl font-bold">Lezioni vuote</h1>
        <p className="text-gray-600">{dataOggi}</p>
      </div>
    );
  }

  const titolo = "Il tuo orario";

  const classeUp = role === 'studente'
    ? lezioni[0]?.nomeClasse || ''
    : '';



  return (
    <div className="container py-4">
      <style jsx>{`
      .table-cell-hover td:hover {
        background-color: #9a9a9aff !important;
        transition: background-color 0.8s ease;
        transform: scale(1.10);
      }
    `}</style>
      <h1 className="fw-bold mb-2">{titolo}</h1>
      <p className="text-muted mb-4">{classeUp}</p>

      {role === 'studente' ? (

        <table className="table table-bordered table-cell-hover table-zoom-hover">
          <thead className="table-dark">

            <tr>
              <th scope="col">#</th>
              <th scope="col">LUNEDI</th>
              <th scope="col">MARTEDI</th>
              <th scope="col">MERCOLEDI</th>
              <th scope="col">GIOVEDI</th>
              <th scope="col">VENERDI</th>
            </tr>
          </thead>
          <tbody>
            {ORE_SCOLASTICHE.map(ora => (
              <tr key={ora.key}>
                <th scope="row">
                  <div style={{ textAlign: 'center' }}>
                    <div>{ora.label}</div>
                    <small style={{ color: '#666', fontSize: '0.8em' }}>{ora.orario}</small>
                  </div>
                </th>
                {['LUNEDI', 'MARTEDI', 'MERCOLEDI', 'GIOVEDI', 'VENERDI'].map(giorno => (
                  <td key={giorno}>
                    {lezioni.find(l => l.giorno === giorno && l.ora === ora.key)?.materia || '-'}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <table className="table table-bordered table-cell-hover table-zoom-hover">
          <thead className="table-dark">

            <tr>
              <th scope="col">#</th>
              <th scope="col">LUNEDI</th>
              <th scope="col">MARTEDI</th>
              <th scope="col">MERCOLEDI</th>
              <th scope="col">GIOVEDI</th>
              <th scope="col">VENERDI</th>
            </tr>
          </thead>
          <tbody>
            {ORE_SCOLASTICHE.map(ora => (
              <tr key={ora.key}>
                <th scope="row">
                  <div style={{ textAlign: 'center' }}>
                    <div>{ora.label}</div>
                    <small style={{ color: '#666', fontSize: '0.8em' }}>{ora.orario}</small>
                  </div>
                </th>
                {['LUNEDI', 'MARTEDI', 'MERCOLEDI', 'GIOVEDI', 'VENERDI'].map(giorno => {
                  const lezione = lezioni.find(l => l.giorno === giorno && l.ora === ora.key);
                  return (
                    <td key={giorno}>
                      {lezione ? (
                        <>
                          <div className="fw-bold">{lezione.nomeClasse}</div>
                          <small className="text-muted">{lezione.materia}</small>
                        </>
                      ) : '-'}
                    </td>
                  );
                })}
              </tr>
            ))}
          </tbody>
        </table>

      )}
    </div>
  );
}