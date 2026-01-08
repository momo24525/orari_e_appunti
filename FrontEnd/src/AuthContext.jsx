import React, { createContext, useState, useEffect } from "react";
import { jwtDecode } from "jwt-decode";

const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [isLogged, setIsLogged] = useState(false);
  const [role, setRole] = useState(null); // "professore" | "studente" | null

  const extractRoleFromToken = (token) => {
    try {
      const decoded = jwtDecode(token);
      console.log("JWT decodificato:", decoded); // Debug temporaneo, puoi rimuoverlo dopo

      if (decoded.roles && Array.isArray(decoded.roles) && decoded.roles.length > 0) {
        const springRole = decoded.roles[0]; // es. "ROLE_PROFESSORE"

        if (springRole === "ROLE_PROFESSORE") {
          return "professore";
        } else if (springRole === "ROLE_STUDENTE") {
          return "studente";
        }
        // Aggiungi altri ruoli se ne hai in futuro
      }

      return null;
    } catch (err) {
      console.error("Errore decodifica token:", err);
      return null;
    }
  };

  useEffect(() => {
    const token = localStorage.getItem("token");
    if (token) {
      const extractedRole = extractRoleFromToken(token);
      setRole(extractedRole);
      setIsLogged(true);
    } else {
      setRole(null);
      setIsLogged(false);
    }
  }, []);

  const login = (token) => {
    localStorage.setItem("token", token);
    const extractedRole = extractRoleFromToken(token);
    setRole(extractedRole);
    setIsLogged(true);
  };

  const logout = () => {
    localStorage.removeItem("token");
    setRole(null);
    setIsLogged(false);
  };

  return (
    <AuthContext.Provider value={{ isLogged, role, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export default AuthContext;