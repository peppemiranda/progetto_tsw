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

import model.ComposizioneOrdineDAODataSource;
import model.OrdineDAODataSource;

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
		
        response.sendRedirect("CatalogoServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        
        UtenteRegistrato utente = (UtenteRegistrato) session.getAttribute("utenteLoggato");
        if (utente == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        
        ArrayList<Scarpa> carrello = (ArrayList<Scarpa>) session.getAttribute("carrello");
        if (carrello == null || carrello.isEmpty()) {
            response.sendRedirect("CatalogoServlet");
            return;
        }
        
        String indirizzo = request.getParameter("indirizzo");
        String citta = request.getParameter("citta");
        String carta = request.getParameter("carta");
        
        if (indirizzo == null || indirizzo.trim().isEmpty() ||
            citta == null || citta.trim().isEmpty() ||
            carta == null || carta.trim().isEmpty()) {
            response.sendRedirect("CheckoutServlet?errore=campi_vuoti");
            return;
        }
        
        try {
            double totale = 0;
            for (Scarpa scarpa : carrello) {
                totale += scarpa.getPrezzoAttuale();
            }
            
            Ordine nuovoOrdine = new Ordine();
            nuovoOrdine.setIdUtente(utente.getIdUtente()); 
            nuovoOrdine.setTotaleOrdine(totale);
            
            OrdineDAODataSource ordineDao = new OrdineDAODataSource();
            ordineDao.doSave(nuovoOrdine); 
            
            ComposizioneOrdineDAODataSource compDao = new ComposizioneOrdineDAODataSource();

            model.ScarpaDAODataSource scarpaDao = new model.ScarpaDAODataSource();

            for (Scarpa scarpa : carrello) {
                ComposizioneOrdine dettaglio = new ComposizioneOrdine();
                dettaglio.setIdOrdine(nuovoOrdine.getIdOrdine()); 
                dettaglio.setIdScarpa(scarpa.getIdScarpa());
                dettaglio.setPrezzoAcquisto(scarpa.getPrezzoAttuale());
                dettaglio.setQuantitaScelta(1); 	
                
                compDao.doSave(dettaglio);	
                
                Scarpa scarpaDalDB = scarpaDao.doRetrieveByKey(scarpa.getIdScarpa());
                if (scarpaDalDB != null && scarpaDalDB.getPezziMagazzino() > 0) {
                    scarpaDalDB.setPezziMagazzino(scarpaDalDB.getPezziMagazzino() - 1);
                    scarpaDao.doUpdate(scarpaDalDB); 
                }
            }
            
            carrello.clear();
            
            response.sendRedirect("CatalogoServlet?acquisto=successo");

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            request.getRequestDispatcher("/WEB-INF/views/common/errore.jsp").forward(request, response);
        }
                   
	}

}
