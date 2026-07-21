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
		
		
        jakarta.servlet.http.HttpSession session = request.getSession();
        
        model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
        
        if (utente == null) {
            response.sendRedirect("LoginServlet");
            return; //Fermiamo l'esecuzione del codice
        }
        
        java.util.ArrayList<model.Scarpa> carrello = (java.util.ArrayList<model.Scarpa>) session.getAttribute("carrello");
        
        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("CatalogoServlet");
            return;
        }
        
        request.getRequestDispatcher("/WEB-INF/views/common/checkout.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
