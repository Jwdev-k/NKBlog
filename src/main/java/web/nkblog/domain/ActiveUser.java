package web.nkblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class ActiveUser {
    private String id;
    private String userId;
}
