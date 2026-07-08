package control;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


//L'annotazione dice a Tomcat quali pagine specifiche deve "controllare" questo Filtro
@WebFilter(urlPatterns = {"/storico.jsp", "/CheckoutServlet", "/StoricoOrdiniServlet", "/carrello.jsp"})
public class AuthFilter extends HttpFilter {
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		boolean loggato;
		
		//Recuperiamo la sessione dell'utente(se esiste), se non esiste NON ne crea una nuova
        HttpSession session = request.getSession(false);
        
        //Verifichiamo se l'utente è regolarmente loggato
        loggato = (session != null && session.getAttribute("utenteLoggato") != null);
		
	}
}
