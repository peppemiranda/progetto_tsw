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
		
		
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String passwordInChiaro = request.getParameter("password");
        String indirizzo = request.getParameter("indirizzo");
        
        if (nome == null || !nome.matches("^[A-Za-z\\s]+$") ||
                cognome == null || !cognome.matches("^[A-Za-z\\s]+$") ||
                email == null || !email.matches("^\\S+@\\S+\\.\\S+$") ||
                passwordInChiaro == null || passwordInChiaro.length() < 8 ||
                indirizzo == null || indirizzo.trim().isEmpty()) {
                
                response.sendRedirect("RegistrazioneServlet?errore=dati_non_validi");
                return;
            }
                
        String passwordHash = util.PasswordHasher.hashPassword(passwordInChiaro);
        
        
        model.UtenteRegistrato nuovoUtente = new model.UtenteRegistrato();
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setEmail(email);
        nuovoUtente.setPasswordHash(passwordHash);
        nuovoUtente.setRuolo("Cliente"); 
        nuovoUtente.setIndirizzoSpedizione(indirizzo);
        
        
        model.UtenteRegistratoDAODataSource dao = new model.UtenteRegistratoDAODataSource();
        try {
            dao.doSave(nuovoUtente);

            response.sendRedirect("LoginServlet");
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 
            
            if (e.getErrorCode() == 1062) {
                response.sendRedirect("RegistrazioneServlet?errore=email_esistente");
            } else {

                request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
            }
        }
        
	}

}
