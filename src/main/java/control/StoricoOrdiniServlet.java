package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class StoricoOrdiniServlet
 */
@WebServlet("/StoricoOrdiniServlet")
public class StoricoOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoricoOrdiniServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Prendiamo la sessione dell'utente
        jakarta.servlet.http.HttpSession session = request.getSession();
        model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
        
        //Se l'utente NON è loggato, lo rispediamo alla pagina di login
        if (utente == null) {
            response.sendRedirect("login.jsp");
            return;  //Fermiamo l'esecuzione
        }

        //Chiamiamo il DAO per gli Ordini
        model.OrdineDAODataSource dao = new model.OrdineDAODataSource();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
