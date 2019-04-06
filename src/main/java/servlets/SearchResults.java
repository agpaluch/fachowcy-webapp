package servlets;

import dao.ProfessionalDetails;
import dao.ProfessionalsDatabaseDaoBean;
import dao.UserCRUDDao;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import repository.RepositoryOfUsers;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/search-results")
public class SearchResults extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    @EJB
    UserCRUDDao userCRUDDao;

    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), "search-results.ftlh");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Integer> map = new HashMap<>();

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, List<ProfessionalDetails>> map = new HashMap<>();
        //map.put("se", req.getParameter("search"));
        String s = req.getParameter("search");

        RepositoryOfUsers.fillDatabase();
        ProfessionalsDatabaseDaoBean pd = RepositoryOfUsers.getProfessionalsDatabaseDaoBean();

        List<ProfessionalDetails> li = pd.getByProfession(s);

        map.put("searchResults", li);

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }
}