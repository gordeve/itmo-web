package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class IndexPage extends Page {
    private void getArticles(HttpServletRequest request, Map<String, Object> view) {
        view.put("articles", articleService.getArticles());
    }
}
