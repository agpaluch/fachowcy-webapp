package servlets;

import dao.UserDao;
import dao.UserDaoBean;
import domain.HibernateUtil;
import domain.UserDetails;
import domain.UserLogin;
import freemarker.TemplateProvider;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.hibernate.SessionFactory;
import repository.RepositoryOfUsers;
import session.SessionInfo;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

@WebServlet("/")
public class Index extends HttpServlet {

    Logger logger = Logger.getLogger(getClass().getName());
    Template template;

    private static final String TEMPLATE_NAME = "index";

    @Inject
    SessionInfo sessionInfo;

    @EJB
    UserDao userDao;

    @Override
    public void init() {
        try {
            template = TemplateProvider.createTemplate(getServletContext(), TEMPLATE_NAME);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        EntityManager em = HibernateUtil.getSessionFactory().createEntityManager();

        EntityTransaction trans = em.getTransaction();
        trans.begin();
        UserDetails ud = userDao.getByProfession("plumber").get(1);

        UserLogin ul = new UserLogin();
        ul.setEmail("aaa@gmail.com");
        ul.setPassword("pass");
        ul.setSignUpDate(LocalDate.of(1991, 10 ,5));

        ud.setUserLogin(ul);
        em.persist(ud);
        trans.commit();

        /*EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        UserLogin ul = new UserLogin();
        ul.setEmail("ccc@gmail.com");
        ul.setPassword("pass");
        ul.setSignUpDate(LocalDate.of(1991, 10 ,5));*/
        //ul.setUserDetails(userDao.getByProfession("plumber").get(0));
        //UserDetails ud = userDao.getByProfession("plumber").get(0);
        //em.persist(ud);
        //em.persist(ul);
        //transaction.commit();

        em.close();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = resp.getWriter();

        Map<String, Object> map = new HashMap<>();
        map.put("content", "index");
        map.put("sessionInfo", sessionInfo);


        try {
            template.process(map, printWriter);
        } catch (TemplateException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
