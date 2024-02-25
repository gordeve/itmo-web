package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.TalkService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@SuppressWarnings({"unused"})
public class TalksPage extends Page {
    private final TalkService talkService = new TalkService();
    @Override
    protected final void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (isNull(getUser())) {
            setMessage("You need to log in to access this page.");
            throw new RedirectException("/index");
        }
        view.put("users", userService.findAll());
    }
    private void sendMessage(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String targetId = request.getParameter("targetUserId");
        String text = request.getParameter("text");
        long id = talkService.validateTalk(userService, getUser(), targetId, text);
        User target = new User();
        target.setId(id);
        talkService.save(getUser(), target, text);
    }

    @Override
    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
        List<Talk> talks = talkService.getUserTalks(getUser());
        if (!talks.isEmpty()) {
            for (Talk talk : talks) {
                talk.setSourceLogin(userService.findById(talk.getSourceUserId()).getLogin());
                talk.setTargetLogin(userService.findById(talk.getTargetUserId()).getLogin());
            }
            view.put("talks", talks);
        }
    }
}
