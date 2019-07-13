package filters;

import session.SessionInfo;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(
        filterName = "ContactFilter",
        urlPatterns = {"/contact-prof"}
        //initParams = @WebInitParam(name = "minSalary", value = "100")
)
public class ContactFilter extends HttpFilter {

    @Inject
    SessionInfo sessionInfo;

    public void destroy() {
    }

    private FilterConfig context;

    public void init(FilterConfig filterConfig) {
        this.context = filterConfig;
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        
        if(sessionInfo.getEmail() == null) {
            resp.sendRedirect("/login-form");
        } else {
            chain.doFilter(req, resp);
        }
    }

}
