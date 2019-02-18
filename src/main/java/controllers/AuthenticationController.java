package controllers;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/auth")
public class AuthenticationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = (String) req.getParameter("username");
        String password = (String) req.getParameter("password");

        Instagram4j instagram = Instagram4j
                .builder()
                .username(username)
                .password(password).build();

        instagram.setup();
        InstagramLoginResult loggedUser = instagram.login();

        InstagramSearchUsernameResult user = instagram.sendRequest(
                new InstagramSearchUsernameRequest(loggedUser.getLogged_in_user().getUsername()));

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("instagram", instagram);

       resp.sendRedirect(req.getContextPath()+"/followers");

    }
}
