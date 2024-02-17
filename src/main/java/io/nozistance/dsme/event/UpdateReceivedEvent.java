package io.nozistance.dsme.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class UpdateReceivedEvent extends ApplicationEvent {

    private final Update update;

    public UpdateReceivedEvent(Object source, Update update) {
        super(source);
        this.update = update;
    }
}
