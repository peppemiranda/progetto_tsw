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
			request.getRequestDispatcher("/WEB-INF/views/common/registrazione.jsp")
			       .forward(request,response);
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
            response.sendRedirect("LoginServlet");
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 	// In caso di errore nel DB, stampiamo il problema nella console
            
            //Se l'errore SQL è un duplicato (codice 1062) rimanda alla JSP col messaggio "Email esistente"
            if (e.getErrorCode() == 1062) {
                response.sendRedirect("RegistrazioneServlet?errore=email_esistente");
            } else {
            	
                // Altrimenti, per tutti gli altri crash(come abbiamo fatto in tutte
            	// le altre Servlet), facciamo il forward sicuro alla pagina di errore
                request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
            }
        }
        
	}

}
