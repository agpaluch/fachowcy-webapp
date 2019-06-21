package servlets;

import dao.UserLoginDAO;
import domain.UserDTO;
import domain.UserDetails;
import domain.UserLogin;
import freemarker.template.Template;
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
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/edit-user")
public class EditUserServlet extends HttpServlet{

    private Logger logger = Logger.getLogger(getClass().getName());
    private Template template;
    private Map<String, Object> dataMap = new HashMap<>();
    private Map<String, String> mapOfErrors = new HashMap<>();
    private Map<String, Object> mapOfValues = new HashMap<>();
    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserLoginDAO userLoginDAO;


    public void init(){
        template = FreemarkerUtil.createTemplate(TEMPLATE_NAME, logger, getServletContext());
    }


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        String emailToEdit = req.getParameter("emailOfUserToEdit");
        Optional<UserLogin> userToEdit = userLoginDAO.getByLogin(emailToEdit);


        Map<String, Object> map = new HashMap<>();
        map.put("content", "edit-user");
        map.put("sessionInfo", sessionInfo);

        List<Field> fields = Stream.concat(Arrays.stream(UserLogin.class.getDeclaredFields()),
                Arrays.stream(UserDetails.class.getDeclaredFields()))
                .filter(f -> !f.getName().equals("userDetails"))
                .collect(Collectors.toList());

        mapOfErrors = fields.stream().map(Field::getName).collect(Collectors.toMap(Function.identity(), n -> ""));

        String names = fields.toString();
        System.out.println("uwaga");

        if (userToEdit.isPresent()){
            map.put("inputData", userToEdit.get());
        }

        FreemarkerUtil.processData(resp, template, map, logger);


    }



    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
