package control;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ComposizioneOrdine;
import model.ComposizioneOrdineDAODataSource;
import model.Ordine;
import model.OrdineDAODataSource;
import model.Scarpa;
import model.UtenteRegistrato;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Servlet implementation class ConfermaOrdineServlet
 */
@WebServlet("/ConfermaOrdineServlet")
public class ConfermaOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConfermaOrdineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Impediamo l'accesso diretto via GET
        response.sendRedirect("CatalogoServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        
        //Verifichiamo che l'utente sia loggato
        UtenteRegistrato utente = (UtenteRegistrato) session.getAttribute("utenteLoggato");
        if (utente == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        
        //Verifichiamo che il carrello non sia vuoto
        ArrayList<Scarpa> carrello = (ArrayList<Scarpa>) session.getAttribute("carrello");
        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("CatalogoServlet");
            return;
        }
        
        //Ci prendiamo i parametri inseriti dall'utente nel form di checkout
        String indirizzo = request.getParameter("indirizzo");
        String citta = request.getParameter("citta");
        String carta = request.getParameter("carta");
        
        //Validazione di sicurezza lato server: se i campi obbligatori sono vuoti rimandiamo l'utente indietro
        if (indirizzo == null || indirizzo.trim().isEmpty() ||
            citta == null || citta.trim().isEmpty() ||
            carta == null || carta.trim().isEmpty()) {
            response.sendRedirect("CheckoutServlet?errore=campi_vuoti");
            return;
        }
        
        try {
            //Calcoliamo il totale dell'ordine scorrendo le scarpe nel carrello
            double totale = 0;
            for (Scarpa scarpa : carrello) {
                totale += scarpa.getPrezzoAttuale();
            }
            
            //Creiamo l'oggetto Ordine
            Ordine nuovoOrdine = new Ordine();
            nuovoOrdine.setIdUtente(utente.getIdUtente()); // Colleghiamo l'ordine al cliente registrato
            nuovoOrdine.setTotaleOrdine(totale);
            
            //Chiamiamo il DAO e salviamo l'ordine
            OrdineDAODataSource ordineDao = new OrdineDAODataSource();
            ordineDao.doSave(nuovoOrdine); 

            //Salviamo le singole righe dell'ordine con Composizione_Ordine
            ComposizioneOrdineDAODataSource compDao = new ComposizioneOrdineDAODataSource();
            for (Scarpa scarpa : carrello) {
                ComposizioneOrdine dettaglio = new ComposizioneOrdine();
                dettaglio.setIdOrdine(nuovoOrdine.getIdOrdine()); //ID recuperato dopo l'inserimento
                dettaglio.setIdScarpa(scarpa.getIdScarpa());
                dettaglio.setPrezzoAcquisto(scarpa.getPrezzoAttuale());
                dettaglio.setQuantitaScelta(1); 	//Manteniamo 1 quantita fissa
                
                compDao.doSave(dettaglio);	//Salviamo il tutto
            }
            
            //Appena l'utente completa l'acquisto svuotiamo il carrello
            carrello.clear();
            
            //Rimandiamo l'utente Controller del catalogo
            response.sendRedirect("CatalogoServlet?acquisto=successo");

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            //Errore SQL: facciamo un forward alla pagina di errore (protetta in WEB-INF)
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
                   
	}

}
