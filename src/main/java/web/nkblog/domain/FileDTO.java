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
public class FileDTO implements Serializable {
    private static final long serialVersionUID = 4001277246927139744L;

    private String filename;
    private byte[] data;
}
