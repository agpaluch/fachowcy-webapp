package com.isa.sklepinternetowy.images;

import dao.UserLoginDAO;
import domain.UserLogin;
import lombok.SneakyThrows;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/images/*")
public class ImagesServlet extends HttpServlet {

    @EJB
    UserLoginDAO userLoginDAO;

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<UserLogin> maybeUser = userLoginDAO.getByLogin("professional1@gmail.com");
        Blob blob = maybeUser.get().getUserDetails().getProfilePicture();
        resp.setContentType("image/gif");

        InputStream inputStream = blob.getBinaryStream();
        OutputStream outputStream = resp.getOutputStream();
        //ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        /*byte[] imageBytes = outputStream.toByteArray();
        resp.getOutputStream().print(imageBytes);*/

        inputStream.close();
        outputStream.close();




/*        OutputStream out = resp.getOutputStream();


        int read = 0;
        final byte[] bytes = new byte[1024];

        while (true) {
            try {
                if (!((read = pic.getBinaryStream().read(bytes)) != -1)) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            out.write(bytes, 0, read);
        }

        out.close();*/

    }
}
