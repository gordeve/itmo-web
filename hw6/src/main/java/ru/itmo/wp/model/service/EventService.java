package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.repository.EventRepository;
import ru.itmo.wp.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private final EventRepository eventRepository = new EventRepositoryImpl();

    public Event save(User user, Event.EventType type) {
        Event event = new Event();
        event.setUserId(user.getId());
        event.setType(type);
        eventRepository.save(event);
        return event;
    }
}
