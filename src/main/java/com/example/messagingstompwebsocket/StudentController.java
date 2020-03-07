package com.example.messagingstompwebsocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.time.LocalTime;

@Controller
public class StudentController {

    private SimpMessagingTemplate simpMessagingTemplate;
    private SimpUserRegistry simpUserRegistry;

    int users = 0;
    int current = 0;

    public StudentController(SimpMessagingTemplate simpMessagingTemplate, SimpUserRegistry simpUserRegistry) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    @MessageMapping("/student/cheer")
    @SendTo("/topic/ratings")
    public Rating greeting(StudentMessage message) throws Exception {
        current = current >= 100 ? current : current + 1;
        return new Rating(current, LocalTime.now());
    }

    @Scheduled(fixedRate = 250)
    public void sendUpdate() {
        simpMessagingTemplate.convertAndSend("/topic/ratings", new Rating(current, LocalTime.now()));
    }

    @Scheduled(fixedRate = 1_000)
    public void reduce() {
        current = current > 0 ? current - users : current;
    }

    @EventListener
    public void handle(SessionSubscribeEvent event) {
        users++;
    }

}
