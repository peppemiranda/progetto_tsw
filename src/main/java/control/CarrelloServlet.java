package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CarrelloServlet
 */
@WebServlet("/CarrelloServlet")
public class CarrelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarrelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperiamo la Sessione dell'utente. Se la sessione esiste ci viene restituita, altrimenti se ne crea una nuova
        jakarta.servlet.http.HttpSession session = request.getSession();
        
        //Cerchiamo il carrello nella sessione.
        // Usiamo una ArrayList di Java per contenere le scarpe scelte
        
        java.util.ArrayList<model.Scarpa> carrello = (java.util.ArrayList<model.Scarpa>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new java.util.ArrayList<>();
            session.setAttribute("carrello", carrello); // Lo appiccichiamo alla sessione
        }
        
        //Leggiamo dall'URL cosa vuole fare l'utente
        String azione = request.getParameter("azione");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
