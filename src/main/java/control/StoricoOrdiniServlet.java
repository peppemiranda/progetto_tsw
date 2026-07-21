package control;

import jakarta.servlet.ServletException;
import model.Ordine;
import model.OrdineDAODataSource;
import model.UtenteRegistrato;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

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
		
		HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("utenteLoggato") == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        UtenteRegistrato utente = (UtenteRegistrato) session.getAttribute("utenteLoggato");
        OrdineDAODataSource dao = new OrdineDAODataSource();

        try {

            Collection<Ordine> ordini = dao.doRetrieveByUtente(utente.getIdUtente());
            
            request.setAttribute("listaOrdini", ordini);
            
            request.getRequestDispatcher("/WEB-INF/views/common/storico.jsp").forward(request, response);
            
        } catch (SQLException e) {
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
