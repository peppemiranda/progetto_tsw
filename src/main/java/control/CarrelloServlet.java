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
		
        jakarta.servlet.http.HttpSession session = request.getSession();
        
        
        java.util.ArrayList<model.Scarpa> carrello = (java.util.ArrayList<model.Scarpa>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new java.util.ArrayList<>();
            session.setAttribute("carrello", carrello); 
        }
        
        request.getRequestDispatcher("/WEB-INF/views/common/carrello.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		jakarta.servlet.http.HttpSession session = request.getSession();
        
        java.util.ArrayList<model.Scarpa> carrello = (java.util.ArrayList<model.Scarpa>) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new java.util.ArrayList<>();
            session.setAttribute("carrello", carrello);
        }

        String azione = request.getParameter("azione");

        if (azione != null) {
            try {
                if (azione.equals("aggiungi")) {
                    int idScarpa = Integer.parseInt(request.getParameter("id"));
                    model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
                    model.Scarpa scarpaDaAggiungere = dao.doRetrieveByKey(idScarpa);
                    
                    if (scarpaDaAggiungere != null) {
                        carrello.add(scarpaDaAggiungere);
                    }
                } 
                else if (azione.equals("rimuovi")) {
                    int idScarpa = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < carrello.size(); i++) {
                        if (carrello.get(i).getIdScarpa() == idScarpa) {
                            carrello.remove(i);
                            break;
                        }
                    }
                }
                else if (azione.equals("svuota")) {
                    carrello.clear();
                }
            } catch (NumberFormatException | java.sql.SQLException e) {
                e.printStackTrace();
                request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
                return;
            }
        }
        
        response.sendRedirect("CarrelloServlet");
	}

}
