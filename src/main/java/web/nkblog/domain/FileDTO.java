package web.nkblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FileDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 4001277246927139744L;

    private int bno;
    private String filename;
    private byte[] data;
}
