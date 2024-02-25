package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    private final String LOCAL_PATH = "C:/Users/gorde/Desktop/web/hw3/src/main/webapp/static/";
    private final String STATIC_PATH = "/static/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String request_uri = request.getRequestURI().substring(1);
        String[] uris = request_uri.split("\\+", 0);

        response.setContentType(getServletContext().getMimeType(getFileFromUri(uris[0]).getName()));

        for (String uri : uris) {
            File file = getFileFromUri(uri);
            if (!file.isFile()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }

        for (String uri : uris) {
            File file = getFileFromUri(uri);
            try (OutputStream outputStream = response.getOutputStream()) {
                Files.copy(file.toPath(), outputStream);
            }
        }
    }

    private File getFileFromUri(String uri) {
        File file = new File(LOCAL_PATH, uri);
        if (!file.isFile()) {
            file = new File(getServletContext().getRealPath(STATIC_PATH + uri));
        }
        return file;
    }
}
