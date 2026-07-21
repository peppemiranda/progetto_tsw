package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class DettaglioScarpaServlet
 */
@WebServlet("/DettaglioScarpaServlet")
public class DettaglioScarpaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DettaglioScarpaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String idStringa = request.getParameter("id");
        
        if (idStringa != null && !idStringa.isEmpty()) {
        	
        	try {
        		
                int idScarpa = Integer.parseInt(idStringa); 

                model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
                model.Scarpa scarpaTrovata = dao.doRetrieveByKey(idScarpa);

                if (scarpaTrovata != null) {
                    request.setAttribute("scarpa", scarpaTrovata);

                    jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/dettaglio_prodotto.jsp");
                    dispatcher.forward(request, response);
                    
                } else {	//La scarpa NON è presente nel database
              
                    response.sendRedirect("CatalogoServlet");
                }

            } catch (NumberFormatException e) {
            	
                response.sendRedirect("CatalogoServlet");
                
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
            }
            
        } else {
            response.sendRedirect("CatalogoServlet");
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
