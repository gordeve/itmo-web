package ru.itmo.wp.servlet;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.Random;

import static ru.itmo.wp.util.ImageUtils.toPng;

public class CaptchaFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        /* Ваша задача написать фильтр CaptchaFilter, который будет перехватывать все GET-запросы,
           если в текущей сессии не отмечено, что пройдена каптча. */
        if (request.getMethod().equals("GET")) {
            if (request.getRequestURI().equals("/captcha/a.png")) {
                response.setContentType("image/png");
                try (OutputStream outputStream = response.getOutputStream()) {
                    outputStream.write(toPng(session.getAttribute("captcha").toString()));
                }
            } else if (request.getRequestURI().equals("/captcha.html")) {
                chain.doFilter(request, response);
            } else if (Objects.isNull(session.getAttribute("captcha-ok"))) {
                int captchaNumber = 100 + new Random().nextInt(900);
                session.setAttribute("captcha", String.valueOf(captchaNumber));
                if (!request.getRequestURI().equals("/captcha.html")) {
                    response.sendRedirect("/captcha.html");
                }
                /*Фильтр должен загадать случайное число от 100 до 999,
                записать это в сессию (ожидаемый ответ) и простейшую форму с картинкой
                и полем для ввода ответа.*/
            } else {
                chain.doFilter(request, response);
            }
        } else if (request.getMethod().equals("POST") && request.getRequestURI().equals("/captcha/check")) {

        } else {
            chain.doFilter(request, response);
        }
        /*

        Пользователь отправляет форму, фильтр ловит ответ и если ответ совпал с ожидаемым,
        то помечает в сессии, что каптча пройдена и больше не перехватывает запросы.

        Иначе перезагадывает число и опять показывает форму.
        */
        /*
        String acceptEncodingHeaderValue = request.getHeader("Accept-Encoding");
        if (acceptEncodingHeaderValue != null
                && acceptEncodingHeaderValue.toLowerCase().contains("gzip")) {
            DelayedHttpServletResponse delayedResponse = new DelayedHttpServletResponse(response);
            response.setHeader("Content-Encoding", "gzip");
            chain.doFilter(request, delayedResponse);
            ServletOutputStream outputStream = response.getOutputStream();
            GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
            gzipOutputStream.write(delayedResponse.getDelayedOutputStream().toByteArray());
            gzipOutputStream.close();
        } else {
            chain.doFilter(request, response);
        }
        */
    }
}
