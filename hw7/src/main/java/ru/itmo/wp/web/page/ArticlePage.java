package ru.itmo.wp.web.page;

import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class ArticlePage extends Page {

    @Override
    protected final void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("You need to log in to access this page");
            throw new RedirectException("/index");
        }
    }

    private void sendArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        if (getUser() == null) {
            return;
        }
        String title = request.getParameter("title");
        String text = request.getParameter("text");
        articleService.validateArticle(title, text);
        articleService.save(title, text, getUser());
        setMessage("Article created!");
        throw new RedirectException("/article");
    }
}
