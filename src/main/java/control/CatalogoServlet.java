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
		
		model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
        String azione = request.getParameter("azione");

        try {
            if ("filtra".equalsIgnoreCase(azione)) {
                
                String marca = request.getParameter("marca");
                String terreno = request.getParameter("terreno");
                String q = request.getParameter("q"); // Cattura la barra di ricerca
                

                java.util.Collection<model.Scarpa> catalogo = dao.doRetrieveByFilter(marca, terreno, q);
                
                //Preparazione della risposta JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                java.io.PrintWriter out = response.getWriter();
                
                StringBuilder json = new StringBuilder("[");
                int count = 0;
                for (model.Scarpa s : catalogo) {
                    json.append("{")
                        .append("\"idScarpa\":").append(s.getIdScarpa()).append(",")
                        .append("\"modello\":\"").append(s.getModello().replace("\"", "\\\"")).append("\",")
                        .append("\"prezzoAttuale\":").append(s.getPrezzoAttuale()).append(",")
                        .append("\"immagine\":\"").append(s.getImmagine().replace("\"", "\\\"")).append("\"")
                        .append("}");
                    
                    if (++count < catalogo.size()) {
                        json.append(",");
                    }
                }
                json.append("]");
                
                out.print(json.toString());
                out.flush();
                return; 
            } 
            
            java.util.Collection<model.Scarpa> catalogo = dao.doRetrieveAll();
            request.setAttribute("listaScarpe", catalogo);
            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/catalogo.jsp");
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
