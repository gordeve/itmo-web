package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.TalkRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;
import ru.itmo.wp.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

import static java.util.Objects.isNull;

public class TalkService {
    private final TalkRepository talkRepository = new TalkRepositoryImpl();

    public List<Talk> getUserTalks(User user) {
        return talkRepository.findByUserId(user.getId());
    }

    public Talk save(User from, User to, String text) {
        Talk talk = new Talk();
        talk.setSourceUserId(from.getId());
        talk.setTargetUserId(to.getId());
        talk.setText(text);
        talkRepository.save(talk);
        return talk;
    }

    public long validateTalk(UserService userService, User user, String target, String text) throws ValidationException {

        if (!target.chars().allMatch( Character::isDigit )) {
            throw new ValidationException("Target id should be an integer");
        }

        long id = Long.parseLong(target);

        if (id == 0) {
            throw new ValidationException("Please pick a person to receive your message");
        }

        if (isNull(userService.findById(id))) {
            throw new ValidationException("No such user");
        }

        if (Strings.isNullOrEmpty(text)) {
            throw new ValidationException("Message can't be empty");
        }
        return id;
    }
}
