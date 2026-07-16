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
		
		// Leggiamo l'ID della scarpa dall'URL
        String idStringa = request.getParameter("id");
        
        // Controllo di sicurezza: verifichiamo che l'ID esista e non sia vuoto
        if (idStringa != null && !idStringa.isEmpty()) {
        	
        	try {
        		
                // I parametri HTML viaggiano sempre come testo(String), quindi convertiamo in un Numero Intero(int)
                // per passarlo a MySQL.
                int idScarpa = Integer.parseInt(idStringa); 

                // Chiamiamo il DAO per cercare la specifica scarpa
                model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
                model.Scarpa scarpaTrovata = dao.doRetrieveByKey(idScarpa);

                if (scarpaTrovata != null) {
                	//Se la scarpa esiste nel database la mettiamo nella request con l'etichetta "scarpaDettaglio"
                    request.setAttribute("scarpa", scarpaTrovata);

                    //Poi la pagina HTML/JSP che mostrerà la foto e il prezzo
                    jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/common/dettaglio_prodotto.jsp");
                    dispatcher.forward(request, response);
                    
                } else {	//La scarpa NON è presente nel database
              
                    response.sendRedirect("CatalogoServlet");	//Rimandiamo l'utente al Controller del catalogo
                }

            } catch (NumberFormatException e) {
            	
            	//Rimandiamo l'utente al Controller del catalogo
                response.sendRedirect("CatalogoServlet");
                
            } catch (java.sql.SQLException e) {
                e.printStackTrace();	 // Errore di connessione a MySQL
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
