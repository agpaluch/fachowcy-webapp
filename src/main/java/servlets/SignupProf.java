package servlets;

import com.infoshareacademy.ClientDetails;
import com.infoshareacademy.ProfessionalDetails;
import freemarker.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/signup-prof")
public class SignupProf extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), "signup-prof.ftlh");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


   /*     req.setAttribute("errorEmail", "");
        req.setAttribute("errorPassword", "");*/

  /*      RequestDispatcher rd = req.getRequestDispatcher("signup-prof.ftlh");
        rd.forward(req, resp);*/




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
        ClientDetails clientDetails = new ClientDetails();
        ProfessionalDetails professionalDetails = new ProfessionalDetails();

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String name = req.getParameter("name");
   /*     String surname = req.getParameter("surname");
        String profession = req.getParameter("profession");
        String phoneNumberString = req.getParameter("phoneNumber");
        String city = req.getParameter("city");
        String longitudeString = req.getParameter("longitude");
        String latitudeString = req.getParameter("latitude");*/


        //req.setAttribute("error", "error");



        //user.setName(req.getParameter("name"));

        //user.setAge(Integer.parseInt(req.getParameter("age")));

/*        usersRepositoryDao.addUser(user);

        user = usersRepositoryDao.getUserById(4);

        PrintWriter printWriter = resp.getWriter();
        printWriter.write("<!DOCTYPE html><html><body>" + user + "</body></html>");*/
    }




}