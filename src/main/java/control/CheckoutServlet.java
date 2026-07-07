package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Prendiamo la sessione
        jakarta.servlet.http.HttpSession session = request.getSession();
        
        //Estraiamo l'utente loggato(dalla sessione), per vedere CHI sta comprando
        //Infatti ci prendiamo "utenteLoggato" dalla Servlet LoginServlet(dovè lì abbiamo fatto session.setAttribute)
        model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
        
        //Se l'utente è null (non ha fatto il login), lo mandiamo alla pagina di login
        if (utente == null) {
            response.sendRedirect("login.jsp");
            return; //Ferma l'esecuzione del codice
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
