package com.example.demo.Event;

import com.example.demo.Game.Game;
import com.example.demo.Player.Player;
import com.example.demo.Team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public Optional<Event> getEvent(Long id) { return eventRepository.findById(id);
    }

}

