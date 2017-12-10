package ua.challenge.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ActionMessage {
    private long id;
    private String value;
}
