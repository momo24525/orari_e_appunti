import { useState, useEffect } from "react";

export default function Home() {
  const [email, setEmail] = useState("");
  const [role, setRole] = useState("");
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const fetchUser = async () => {
  const token = localStorage.getItem('token');
  
  if (!token) {
    window.location.href = '/login';
    return;
  }

  setError("");
  setLoading(true);

  try {
    const response = await fetch("http://localhost:8080/api/user/me", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        'Authorization': `Bearer ${token}`
      },
    });

    if (!response.ok) {
      // Se il token è scaduto o invalido, rimuovilo e reindirizza
      if (response.status === 401 || response.status === 403) {
        localStorage.removeItem('token');
        localStorage.removeItem('userId');
        window.location.href = '/login';
        return;
      }
      
      const text = await response.text();
      throw new Error(text || "Nessun utente trovato");
    }

    const data = await response.json();
    setEmail(data.email || "");
    const roles = Array.isArray(data.role) ? data.role : data.roles || [];
    setRole(Array.isArray(roles) ? roles.join(", ") : String(roles || ""));

    if (data.token) localStorage.setItem("token", data.token);
    if (data.id) localStorage.setItem("userId", data.id);

    console.log("Utente trovato", data);
  } catch (err) {
    setError(err.message || "Errore durante il recupero dell'utente");
  } finally {
    setLoading(false);
  }
};

   const handleSubmit = async (e) => {
     console.trace("handleSubmit chiamato");
  console.log("evento:", e);
    if (e && e.preventDefault) e.preventDefault();
    setError("");
    setLoading(true);

    // se non c'è token, non chiamo l'API di logout (utente già non autenticato)
    const token = localStorage.getItem('token');
    if (!token) {
      localStorage.removeItem('userId');
      setEmail("");
      setRole("");
      setLoading(false);
      // reindirizza alla login
      window.location.href = '/login';
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/logout", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          'Authorization': `Bearer ${token}`,
        },
        body: JSON.stringify({
          email,
          role,
        }),
      });

      if (!response.ok) {
        throw new Error("Logout non riuscito");
      }

      // supporta sia risposte JSON che plain text (es. "Logout effettuato")
      let data;
      const contentType = response.headers.get("content-type") || "";
      if (contentType.includes("application/json")) {
        data = await response.json();
      } else {
        data = await response.text();
      }

      // esempio: JWT
      localStorage.removeItem("token");
      localStorage.removeItem("userId");
      setEmail("");
      setRole("");
      console.log("Logout OK", data);

      // reindirizza alla pagina di login (adatta il percorso se usi react-router)
      window.location.href = '/login';
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
  const token = localStorage.getItem('token');
  
  // Controlla se c'è un token prima di chiamare l'API
  if (token) {
    fetchUser();
  } else {
    // Reindirizza alla login se non c'è token
    window.location.href = '/';
  }
}, []);

  return (
    <div style={{ maxWidth: 400, margin: "50px auto" }}>
      <h2>BENVENUTO {email}</h2>
      <p>Il tuo ruolo è: <strong>{role || "-"}</strong></p>

      {loading ? (
        <p>Caricamento...</p>
      ) : localStorage.getItem('token') ? (
        <button type="button" onClick={handleSubmit}>Logout</button>
      ) : null}

      {error && <p style={{ color: "red" }}>{"Errore nel logout"}</p>}

    </div>
  );
}