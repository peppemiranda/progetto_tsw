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
		
        String email = request.getParameter("email");
        String passwordInChiaro = request.getParameter("password");
        
        String passwordHash = util.PasswordHasher.hashPassword(passwordInChiaro);
        
        
        model.UtenteRegistratoDAODataSource dao = new model.UtenteRegistratoDAODataSource();
        
        try {
            model.UtenteRegistrato utente = dao.doRetrieveByEmailAndPassword(email, passwordHash);
            
            if (utente != null) {
            	
                request.getSession().setAttribute("utenteLoggato", utente);
                
                response.sendRedirect("CatalogoServlet"); 
                
            } else {	//L'utente NON esiste

                response.sendRedirect("LoginServlet?errore=true");
            }
            
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
	}

}
