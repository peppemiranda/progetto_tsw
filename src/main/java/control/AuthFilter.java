package control;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/CheckoutServlet","/ConfermaOrdineServlet" , "/StoricoOrdiniServlet"})
public class AuthFilter extends HttpFilter {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		boolean loggato;
		
        HttpSession session = request.getSession(false);
        
        loggato = (session != null && session.getAttribute("utenteLoggato") != null);
        
        if (loggato) {

            chain.doFilter(request, response);
            
        } else {  //Se l'utente NON è loggato
        
            response.sendRedirect("LoginServlet");
        }
    }
}
