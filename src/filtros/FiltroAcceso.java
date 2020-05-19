package filtros;

import java.io.IOException;
import java.util.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroSession
 */
public class FiltroAcceso implements Filter {
	
	private HttpServletRequest reqActual = null;
    public FiltroAcceso() {
    	super();
    }

   private boolean isUsuarioNormal(){
	   HttpSession session=reqActual.getSession(false);
   	   return session.getAttribute("rol").equals("usuario");
   }
    private boolean isURILogin(){
    	
    	String URI = reqActual.getRequestURI();
    	return URI.equals("/jruteros/index.xhtml")||URI.equals("/jruteros/");
    	
    }
    private boolean esURIPublica(){
      String URI = reqActual.getRequestURI();
      HashMap<String,HashMap<String,Boolean>> URIS=(HashMap<String,HashMap<String,Boolean>>)this.reqActual.getServletContext().getAttribute("URIS");
      return ( (!URIS.containsKey(URI))  ||(URIS.containsKey(URI)) &&(URIS.get(URI).containsKey("all"))&&(URIS.get(URI).get("all")));
    }
    private boolean estaLogueado(){
    	HttpSession session=reqActual.getSession(false);
    	return ((session != null)&&(session.getAttribute("id") != null));
    }
    private boolean tienePermisosSobreLaURI(){
    	String tipoUser=(String)reqActual.getSession(false).getAttribute("rol");
    	String URI=reqActual.getRequestURI();
    	HashMap<String,HashMap<String,Boolean>> URIS=(HashMap<String,HashMap<String,Boolean>>)this.reqActual.getServletContext().getAttribute("URIS");
        return ((URIS.containsKey(URI))&&(URIS.get(URI).containsKey(tipoUser))&&(URIS.get(URI).get(tipoUser)));
    }
   
	public void destroy() {
	
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
	    this.reqActual = req;
	    System.out.println("--------------------------------");
	    System.out.println("requestURI ="+req.getRequestURI());
	    System.out.println("--------------------------------");
		if(this.esURIPublica()){
			if(isURILogin() &&(this.estaLogueado())){
				res.sendRedirect(req.getContextPath() + "/home.xhtml");
			} else {
			   chain.doFilter(request,response);
			}
		}
		else{
           if((this.estaLogueado()) && (this.tienePermisosSobreLaURI())){
        	      System.out.println("estoy logueado y tengo permisos sobre la uri");
        		   chain.doFilter(request,response);
            }
            else{
            	System.out.println("me rajaron al login ");
        	    res.sendRedirect(req.getContextPath() + "/index.xhtml");
            }
        }
       
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
