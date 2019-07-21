package filters;

import domain.Role;
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
        filterName = "AdminFilter",
        urlPatterns = {"/admin"}
)
public class AdminFilter extends HttpFilter {

    @Inject
    SessionInfo sessionInfo;

    public void destroy() {
    }

    private FilterConfig context;

    public void init(FilterConfig filterConfig) {
        this.context = filterConfig;
    }

    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if(sessionInfo.getRole() != Role.ADMIN) {
            resp.sendRedirect("/index");
        } else {
            chain.doFilter(req, resp);
        }
    }

}