package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MessageServlet extends HttpServlet {
    private final String AUTH = "/message/auth";
    private final String FIND_ALL ="/message/findAll";
    private final String ADD = "/message/add";
    private final String USER = "user";

    private ArrayList<Message> messages = new ArrayList<>();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cmd = request.getRequestURI();
        response.setContentType("application/json");
        switch (cmd) {
            case AUTH:
                auth(request, response);
                break;
            case FIND_ALL:
                findAll(response);
                break;
            case ADD:
                add(request);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
        }
    }

    private void add(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = session.getAttribute(USER).toString();
        String text = request.getParameter("text");
        messages.add(new Message(user, text));
    }

    private void findAll(HttpServletResponse response) throws IOException {
        try (PrintWriter writer = response.getWriter()) {
            writer.print(new Gson().toJson(messages));
            writer.flush();
        }
    }

    private void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String user = request.getParameter(USER);
        if (user != null && !user.trim().isEmpty()) {
            session.setAttribute(USER, user);
        }
        if (session.getAttribute(USER) == null) {
            session.setAttribute(USER, "");
        }

        try (PrintWriter writer = response.getWriter()) {
            writer.write(new Gson().toJson(session.getAttribute(USER).toString()));
            writer.flush();
        }
    }

    private static class Message {
        public final String user;
        public final String text;

        public Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }
}
