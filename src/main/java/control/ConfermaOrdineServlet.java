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
            
            java.util.Map<Integer, ComposizioneOrdine> dettagliDaSalvare = new java.util.HashMap<>();

            for (Scarpa scarpa : carrello) {
                int idS = scarpa.getIdScarpa();
                
                if (dettagliDaSalvare.containsKey(idS)) {
                    ComposizioneOrdine dettEsistente = dettagliDaSalvare.get(idS);
                    dettEsistente.setQuantitaScelta(dettEsistente.getQuantitaScelta() + 1);
                } else {
                    ComposizioneOrdine nuovoDettaglio = new ComposizioneOrdine();
                    nuovoDettaglio.setIdOrdine(nuovoOrdine.getIdOrdine()); 
                    nuovoDettaglio.setIdScarpa(idS);
                    nuovoDettaglio.setPrezzoAcquisto(scarpa.getPrezzoAttuale());
                    nuovoDettaglio.setQuantitaScelta(1); 
                    
                    dettagliDaSalvare.put(idS, nuovoDettaglio);
                }
            }

            for (ComposizioneOrdine dettaglio : dettagliDaSalvare.values()) {
                compDao.doSave(dettaglio);  
                
                Scarpa scarpaDalDB = scarpaDao.doRetrieveByKey(dettaglio.getIdScarpa());
                if (scarpaDalDB != null && scarpaDalDB.getPezziMagazzino() > 0) {
                    int nuovaQuantita = scarpaDalDB.getPezziMagazzino() - dettaglio.getQuantitaScelta();
                    if (nuovaQuantita < 0) {
                        nuovaQuantita = 0;
                    }
                    scarpaDalDB.setPezziMagazzino(nuovaQuantita);
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
