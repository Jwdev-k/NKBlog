package web.nkblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class commentDTO implements Serializable {
    private static final long serialVersionUID = 6140055595084923363L;

    private int bno;
    private String uid;
    private String comment;
}
