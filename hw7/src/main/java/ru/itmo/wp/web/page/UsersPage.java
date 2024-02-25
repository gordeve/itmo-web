package ru.itmo.wp.web.page;

import ru.itmo.wp.web.annotation.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused"})
public class UsersPage extends Page {
    @Override
    protected final void action(HttpServletRequest request, Map<String, Object> view) {
        // No operations.
    }

    @Json
    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        view.put("users", userService.findAll());
    }
}
