package controllers;

import org.brunocvcunha.instagram4j.Instagram4j;
import services.FollowersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/unfollow")
public class UnfollowController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        long id = Long.parseLong(req.getParameter("userId"));
        Instagram4j instagram = (Instagram4j) session.getAttribute("instagram");

        new FollowersService(instagram).unfollow(id);
    }
}
