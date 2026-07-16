package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Scarpa;
import model.UtenteRegistrato;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ConfermaOrdineServlet
 */
@WebServlet("/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Impediamo l'accesso diretto via GET
        response.sendRedirect("CatalogoServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        
        //Verifichiamo che l'utente sia loggato
        UtenteRegistrato utente = (UtenteRegistrato) session.getAttribute("utenteLoggato");
        if (utente == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        
        //Verifichiamo che il carrello non sia vuoto
        ArrayList<Scarpa> carrello = (ArrayList<Scarpa>) session.getAttribute("carrello");
        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("CatalogoServlet");
            return;
        }
	}

}
