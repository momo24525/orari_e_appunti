import React, { useEffect, useState } from 'react';



export default function Studenti() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [Studenti, setStudenti] = useState([]);

  useEffect(() => {
    const token = localStorage.getItem("token");
    if(!token) {setError("Non autenticato"); setLoading(false); return;}

    fetch('http://localhost:8080/api/studenti', {
      headers: {
        "Authorization": `Bearer ${token}`
      }
    })
      .then(res => {
        if (!res.ok) throw new Error(res.statusText || 'Errore nella risposta');
        return res.json();
      })
      .then(data => setStudenti(data))
      .catch(err => setError(err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div>Caricamento...</div>;
  if (error) return <div>Errore: {error}</div>;
  if (!Studenti.length) return <div>Nessun studente trovato</div>;

  return (
    <div className="studenti-grid">
      {Studenti.map(s => (
        <div className="studente-card" key={s.id}>
          <h3>{s.nome} {s.cognome} </h3>
          <p>{s.email}, {s.classeId}</p>
        </div>
      ))}
    </div>
  );
}