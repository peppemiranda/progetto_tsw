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
		
        jakarta.servlet.http.HttpSession session = request.getSession();
        model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
        
        if (utente == null) {
            response.sendRedirect("LoginServlet");
            return;  //Fermiamo l'esecuzione
        }

        model.OrdineDAODataSource dao = new model.OrdineDAODataSource();
        
        try {
            java.util.Collection<model.Ordine> mieiOrdini = dao.doRetrieveByUtente(utente.getIdUtente());

            request.setAttribute("listaOrdini", mieiOrdini);

            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/storico.jsp");
            dispatcher.forward(request, response);

        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
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
