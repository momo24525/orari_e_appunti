
document.addEventListener('DOMContentLoaded', () => {

    const loginForm = document.getElementById('login-form');
    const messageElement = document.getElementById('message');

    // Aggiungi un "ascoltatore" per l'evento di invio del form
    loginForm.addEventListener('submit', async (event) => {
        // Impedisce al form di ricaricare la pagina (comportamento di default)
        event.preventDefault();

        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        try {
            // Eseguiamo la stessa richiesta che facevamo con Postman
            const response = await fetch('/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                // Se la risposta è 200 OK, la login ha avuto successo
                const data = await response.json();

                // Salviamo il token nel "localStorage" del browser.
                // È un piccolo spazio di archiviazione che persiste anche se chiudi la pagina.
                localStorage.setItem('jwt-token', data.token);

                messageElement.textContent = 'Login effettuato con successo!';
                messageElement.style.color = 'green';

                // Qui, in un'app reale, reindirizzeresti l'utente a una dashboard.
                // window.location.href = '/dashboard';

            } else {
                // Se la risposta è un errore (es. 403), le credenziali sono sbagliate
                messageElement.textContent = 'Email o password non validi.';
                messageElement.style.color = 'red';
            }
        } catch (error) {
            // Gestisce errori di rete (es. server non raggiungibile)
            console.error('Errore durante la richiesta di login:', error);
            messageElement.textContent = 'Errore di connessione. Riprova più tardi.';
            messageElement.style.color = 'red';
        }
    });
});