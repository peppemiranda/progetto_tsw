package control;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/AdminServlet"})
public class AdminFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
		throws IOException, ServletException {
		
		boolean isLoggato;
		
		
        HttpSession session = request.getSession(false);
        
        
        isLoggato = (session != null && session.getAttribute("utenteLoggato") != null);
        
        if (isLoggato) {
        	
        	
            model.UtenteRegistrato utente = (model.UtenteRegistrato) session.getAttribute("utenteLoggato");
            
            
            if ("Admin".equalsIgnoreCase(utente.getRuolo())) {
            	
                
                chain.doFilter(request, response);
                
            } else {
               
                response.sendRedirect("CatalogoServlet");
            }
            
        } else {  
        	
            response.sendRedirect("LoginServlet");
        }
	}
}
