package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unused"})
public class MyArticlesPage extends Page {
    @Override
    protected final void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        List<Article> articles = articleService.getUserArticles(getUser());
        if (!articles.isEmpty()) {
            view.put("articles", articles);
        }
    }
}
