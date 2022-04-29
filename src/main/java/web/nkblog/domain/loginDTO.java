package web.nkblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class loginDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2644838765824706103L;
    private String uid;
    private String password;
    private String gender;
}
