package org.iris_events.demo;

import jakarta.enterprise.context.ApplicationScoped;
import org.iris_events.annotations.Message;
import org.iris_events.annotations.MessageHandler;
import org.iris_events.annotations.Scope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

@ApplicationScoped
public class EventConsumers {
    private static final Logger log = LoggerFactory.getLogger(EventConsumers.class);

    @MessageHandler
    public HelloEvent sayHello(SayHelloEvent req) {
        log.info("got getHello event: {}", req);
        return new HelloEvent(req.userName(), Instant.now());
    }

    //define events
    @Message(name = "hello-event", scope = Scope.USER)
    public record HelloEvent(String userName, Instant greetedAt) {
    }

    //message handler

    @Message(scope = Scope.FRONTEND, name = "say-hello")
    public record SayHelloEvent(String userName) {
    }


}
