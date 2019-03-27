package filters;

import com.infoshareacademy.ProfessionalsDatabase;
import dao.ProfessionalsDatabaseDaoBean;
import repository.RepositoryOfUsers;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "FilterSignupProf",
        urlPatterns = {"/signup-prof"})
public class FilterSignupProf implements Filter {

/*    @Inject
    RepositoryOfUsers existingDatabases;*/


    private FilterConfig context;

    public void init(FilterConfig config) throws ServletException {
        this.context = config;
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletResponse httpResponse = (HttpServletResponse) resp;
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {

            //Validate e-mail
            if (!validateEmail(req.getParameter("email"))) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            //Check if such e-mail exists in the database
            if (!checkIfEmailExists(req.getParameter("email"))) {
                req.setAttribute("errorEmail", "Adres e-mail już istnieje w bazie.");
            }

            //Validate if confirmPassword is the same as password
            if (!validatePassword(req.getParameter("password"), req.getParameter("confirmPassword"))) {
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }

            RequestDispatcher rd = req.getRequestDispatcher("signup-prof.ftlh");
            rd.include(req, resp);


        }

        chain.doFilter(req, resp);
    }


    //TODO: check if such e-mail exists in database
    public boolean validateEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public boolean checkIfEmailExists(String email) {
        RepositoryOfUsers.fillDatabase();
        ProfessionalsDatabaseDaoBean professionalsDatabaseDaoBean = RepositoryOfUsers.getProfessionalsDatabaseDaoBean();
        boolean result = true;
        if (professionalsDatabaseDaoBean.getProfessionalLogin().containsKey(email)) result = false;
        return result;
    }


    public boolean validatePassword(String password, String confirmPassword) {
        return (password.equals(confirmPassword));
    }


}
