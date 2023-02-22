package tg.alexv100.pagecounter.data.RequestEntity;

import lombok.Data;
import java.util.List;

@Data
public class RequestDTO {
    private String url;
    private String[] formats;
}
