package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class RegistrazioneServlet
 */
@WebServlet("/RegistrazioneServlet")
public class RegistrazioneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrazioneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Recuperiamo i dati inseriti dall'utente nel form HTML
		
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String passwordInChiaro = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        
        
        // Sicurezza: Crittografiamo la password
        String passwordHash = util.PasswordHasher.hashPassword(passwordInChiaro);
        
        
        // Riempiamo l'oggetto con i dati e la password sicura
        
        model.UtenteRegistrato nuovoUtente = new model.UtenteRegistrato();
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setEmail(email);
        nuovoUtente.setPasswordHash(passwordHash);
        nuovoUtente.setRuolo("Cliente"); 	// Di default chi si registra è un utente standard
        nuovoUtente.setIndirizzoSpedizione(indirizzo);
        
        
        // Consegniamo i dati al DAO per salvare ciò fisicamente nel database
        
        model.UtenteRegistratoDAODataSource dao = new model.UtenteRegistratoDAODataSource();
        try {
            dao.doSave(nuovoUtente);
            
            // Se il database salva tutto correttamente, reindirizziamo l'utente alla pagina di login
            response.sendRedirect("login.jsp"); 
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 	// In caso di errore nel DB, stampiamo il problema nella console
            response.sendRedirect("errore.jsp"); 	// E deviamo l'utente su una pagina di errore
        }
        
	}

}
