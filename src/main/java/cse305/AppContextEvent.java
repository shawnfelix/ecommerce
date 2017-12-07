package cse305;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AppContextEvent {
    @EventListener(ContextRefreshedEvent.class)
    void contextRefreshedEvent() {
        //do whatever
    }
}
