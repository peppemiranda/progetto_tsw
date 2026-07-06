package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class CatalogoServlet
 */
@WebServlet("/CatalogoServlet")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Chiamiamo il DAO, per le scarpe
        model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
        
       
        try {
        	
     // Ordiniamo al DAO di estrarre TUTTO il catalogo da MySQL (usiamo la Collection, il metodo della classe ScarpaDAODataSource)
            java.util.Collection<model.Scarpa> catalogo = dao.doRetrieveAll();

            // Attacchiamo il catalogo alla richiesta(request) per farlo viaggiare verso la pagina web
            request.setAttribute("listaScarpe", catalogo);

            // Con RequestDispatcher passiamo tutto alla pagina HTML/JSP.
            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo.jsp");
            dispatcher.forward(request, response);

        } catch (java.sql.SQLException e) {
            e.printStackTrace(); 	//Errore di connessione
            response.sendRedirect("errore.jsp");
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
