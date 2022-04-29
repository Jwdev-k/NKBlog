package web.nkblog.domain;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class boardDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 5286660557338476244L;

    private int bno;
    private String title;
    private String uid;
    private String created;
    private String content;
    private int available;
}
