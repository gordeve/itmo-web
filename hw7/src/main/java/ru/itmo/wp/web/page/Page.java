package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class Page {
    protected final UserService userService = new UserService();
    protected final ArticleService articleService = new ArticleService();
    protected HttpSession session;
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        view.put("userCount", userService.findCount());
        session = request.getSession();
        putUser(view);
        putMessage(view);
    }
    protected void after(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }
    private void putUser(Map<String, Object> view) {
        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }
    }

    protected void setMessage(String message) {
        session.setAttribute("message", message);
    }

    protected void setUser(User user) {
        session.setAttribute("user", user);
    }

    protected User getUser() {
        return (User) session.getAttribute("user");
    }
    private void putMessage(Map<String, Object> view) {
        String message = (String) session.getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            session.removeAttribute("message");
        }
    }

    protected void findUser(HttpServletRequest request, Map<String, Object> view) {
        view.put("user",
                userService.find(Long.parseLong(request.getParameter("userId"))));
    }
}
