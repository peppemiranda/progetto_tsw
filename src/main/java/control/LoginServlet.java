package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/common/login.jsp")
	       .forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Apriamo il "pacco" arrivato da Internet
        String email = request.getParameter("email");
        String passwordInChiaro = request.getParameter("password");
        
        // Crittografiamo la password
        String passwordHash = util.PasswordHasher.hashPassword(passwordInChiaro);
        
        
        
        // Chiamiamo il DAO per interrogare MySQL
        model.UtenteRegistratoDAODataSource dao = new model.UtenteRegistratoDAODataSource();
        
        try {
            // Chiediamo al DAO di cercare nel DB un utente con questa email e questa password(crittografata)
            model.UtenteRegistrato utente = dao.doRetrieveByEmailAndPassword(email, passwordHash);
            
            if (utente != null) {	//Controlliamo se l'utente esiste
            	
            	//Se esiste, creiamo all'utente la sessione
                request.getSession().setAttribute("utenteLoggato", utente);
                
                //Lo mandiamo alla home page del sito(al catalogo)
                response.sendRedirect("CatalogoServlet"); 
                
            } else {	//L'utente NON esiste

            	//Quindi(è null) o l'email o la password è sbagliata. Rimandiamo l'utente alla pagina di login,
            	//aggiungendo un segnale di errore nell'URL
                response.sendRedirect("LoginServlet?errore=true");
            }
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 	// Errore interno del database
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
	}

}
