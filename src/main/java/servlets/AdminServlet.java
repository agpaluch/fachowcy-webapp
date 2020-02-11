package servlets;

import template.TemplateProvider;
import dao.UserLoginDAO;
import domain.UserDetails;
import domain.UserLogin;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import session.SessionInfo;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private Logger logger = Logger.getLogger(getClass().getName());
    private Template template;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;


    @Override
    public void init(){
        try {
            template = TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String emailToDelete = req.getParameter("emailOfUserToDelete");

        if(emailToDelete!=null){
            userLoginDAO.deleteByLogin(emailToDelete);
        }

        String emailToEdit = req.getParameter("emailOfUserToEdit");

        if(emailToEdit!=null){
            resp.sendRedirect("/edit-user?emailOfUserToEdit=" + emailToEdit);
        }


        Map<String, Object> map = new HashMap<>();
        map.put("content", "admin");
        map.put("sessionInfo", sessionInfo);
        map.put("error", "");

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }


    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String email = req.getParameter("search");
        Optional<UserLogin> searchedUserLogin = userLoginDAO.getByLogin(email);
        Optional<UserDetails> searchedUserDetails = userLoginDAO.getDetailsByLogin(email);

        Map<String, Object> map = new HashMap<>();
        map.put("content", "admin");
        map.put("sessionInfo", sessionInfo);
        map.put("error", "");

        if (searchedUserLogin.isPresent() && searchedUserDetails.isPresent()){
            map.put("userLogin", searchedUserLogin.get());
            map.put("userDetails", searchedUserDetails.get());
        } else {
            map.put("error", "Nie ma takiego użytkownika w bazie.");
        }

        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }


    }
}
