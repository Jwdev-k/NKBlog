package web.nkblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommentDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 6140055595084923363L;

    private int bno;
    private String uid;
    private String comment;
    private String created;
}
