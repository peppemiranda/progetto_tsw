package control;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//Il Filter controlla "AdminServlet"
@WebFilter(urlPatterns = {"/AdminServlet"})
public class AdminFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		boolean isLoggato;
		
		//Recuperiamo la sessione senza crearne una nuova se non esiste
        HttpSession session = request.getSession(false);
        
        //Verifichiamo se c'è una sessione e se c'è un utente loggato dentro
        isLoggato = (session != null && session.getAttribute("utenteLoggato") != null);
        
        if (isLoggato) {
        	
        	//Estraiamo l'oggetto utente
            model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
            
            //Controlliamo il RUOLO
            if ("Admin".equalsIgnoreCase(utente.getRuolo())) {
            	
                //Quindi se l'utente è l'Admin, allora passa
                chain.doFilter(request, response);
                
            } else {
            	
                // L'utente è loggato ma è un semplice cliente, quindi lo rispediamo al Catalogo
                response.sendRedirect("CatalogoServlet");
            }
            
        } else {  
        	
            // L'utente è un ospite(non loggato), quindi Lo mandiamo al login
            response.sendRedirect("LoginServlet");
        }
	}
}
