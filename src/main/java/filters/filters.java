package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "FilterClientOrProf",urlPatterns = {"/login-form"})
public class filters implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
//        String ClientOrProf =req.getParameter("Login");
//        if(ClientOrProf.equals("Zaloguj jako klient") || ClientOrProf.equals(null)){
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
//            requestDispatcher.forward(req, resp);
//        }
//        else {
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/detail-prof");
//            requestDispatcher.forward(req, resp);
//        }
//    chain.doFilter(req,resp);
   }

    public void init(FilterConfig config) throws ServletException {

    }

}
