package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ordine;
import model.OrdineDAODataSource;
import model.Scarpa;
import model.ScarpaDAODataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String azione = request.getParameter("azione");
        ScarpaDAODataSource scarpaDAO = new ScarpaDAODataSource();
        OrdineDAODataSource ordineDAO = new OrdineDAODataSource();

        try {
            if ("gestioneCatalogo".equalsIgnoreCase(azione)) {
            	
                Collection<Scarpa> catalogo = scarpaDAO.doRetrieveAll();
                request.setAttribute("listaScarpe", catalogo);
                
                String idModifica = request.getParameter("idModifica");
                if (idModifica != null && !idModifica.isEmpty()) {
                    Scarpa scarpaDaModificare = scarpaDAO.doRetrieveByKey(Integer.parseInt(idModifica));
                    request.setAttribute("scarpaDaModificare", scarpaDaModificare);
                }

                request.getRequestDispatcher("/WEB-INF/views/admin/gestione_catalogo.jsp").forward(request, response);
                
            } else if ("reportOrdini".equalsIgnoreCase(azione)) {
                String dataInizio = request.getParameter("dataInizio");
                String dataFine = request.getParameter("dataFine");
                String idClienteStr = request.getParameter("idCliente");

                Collection<Ordine> ordini;
                
                ordini = ordineDAO.doRetrieveAll(); 
                
                request.setAttribute("listaOrdini", ordini);
                request.getRequestDispatcher("/WEB-INF/views/admin/report_ordini.jsp").forward(request, response);
                
            } else {
            	
                request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operazione = request.getParameter("operazione");
        ScarpaDAODataSource scarpaDAO = new ScarpaDAODataSource();

        try {
            if ("aggiungi".equalsIgnoreCase(operazione)) {
                Scarpa nuova = new Scarpa();
                nuova.setMarca(request.getParameter("marca"));
                nuova.setModello(request.getParameter("modello"));
                nuova.setTerreno(request.getParameter("terreno"));
                nuova.setPrezzoAttuale(Double.parseDouble(request.getParameter("prezzo")));
                nuova.setPezziMagazzino(Integer.parseInt(request.getParameter("pezziMagazzino")));
                nuova.setImmagine(request.getParameter("immagine"));

                scarpaDAO.doSave(nuova);
                response.sendRedirect("AdminServlet?azione=gestioneCatalogo");

            } else if ("modifica".equalsIgnoreCase(operazione)) {
                int id = Integer.parseInt(request.getParameter("idScarpa"));
                Scarpa esistente = scarpaDAO.doRetrieveByKey(id);
                
                if (esistente != null) {
                    esistente.setMarca(request.getParameter("marca"));
                    esistente.setModello(request.getParameter("modello"));
                    esistente.setTerreno(request.getParameter("terreno"));
                    esistente.setPrezzoAttuale(Double.parseDouble(request.getParameter("prezzo")));
                    esistente.setPezziMagazzino(Integer.parseInt(request.getParameter("pezziMagazzino")));
                    esistente.setImmagine(request.getParameter("immagine"));
                    
                    scarpaDAO.doUpdate(esistente); 
                }
                response.sendRedirect("AdminServlet?azione=gestioneCatalogo");

            } else if ("elimina".equalsIgnoreCase(operazione)) {
                int id = Integer.parseInt(request.getParameter("idScarpa"));
                scarpaDAO.doDelete(id); 
                response.sendRedirect("AdminServlet?azione=gestioneCatalogo");
                
            } else {
                response.sendRedirect("AdminServlet");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
	}

}
