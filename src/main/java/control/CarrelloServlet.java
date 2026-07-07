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
            session.setAttribute("carrello", carrello); 	//Lo appiccichiamo alla sessione
        }

        //Leggiamo dall'URL cosa vuole fare l'utente
        String azione = request.getParameter("azione");

        if (azione != null) {
        	
            try {
                if (azione.equals("aggiungi")) {	//L'utente vuole inserire una scarpa
                	
                    int idScarpa = Integer.parseInt(request.getParameter("id"));
                    
                    //Chiamiamo il DAO per prendere i dati della scarpa (marca, prezzo) dal database
                    model.ScarpaDAODataSource dao = new model.ScarpaDAODataSource();
                    model.Scarpa scarpaDaAggiungere = dao.doRetrieveByKey(idScarpa);
                    
                    if (scarpaDaAggiungere != null) {
                        carrello.add(scarpaDaAggiungere);	//Mettiamo nel carrello la scarpa
                    }
                } 
                
                else if (azione.equals("rimuovi")) {	//L'utente vuole togliere una scarpa

                    int idScarpa = Integer.parseInt(request.getParameter("id"));
                    
                    //Cerchiamo la scarpa nel carrello e la eliminiamo
                    for (int i = 0; i < carrello.size(); i++) {
                        if (carrello.get(i).getIdScarpa() == idScarpa) {
                            carrello.remove(i);
                            break;	// Ci fermiamo appena viene tolta una scarpa, quindi se l'utente ne aveva aggiunte due uguali,
                            		// l'altra rimane
                        }
                    }
                }
                
                else if (azione.equals("svuota")) {		//L'utente vuole svuotare tutto in un colpo solo
                    carrello.clear();
                }
            } catch (NumberFormatException | java.sql.SQLException e) {
            	
                //Se toccando l'URL o il DB si ha un problema, intercettiamo l'errore senza far crashare nulla
                e.printStackTrace();
            }
        }
        
        //Rimandiamo l'utente alla pagina del carrello.
        //Usiamo sendRedirect(e non RequestDispatcher) perchè il carrello è nella sessione, quindi la pagina lo troverà da sola
        response.sendRedirect("carrello.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
