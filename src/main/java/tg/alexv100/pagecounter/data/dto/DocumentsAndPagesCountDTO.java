package tg.alexv100.pagecounter.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DocumentsAndPagesCountDTO {
    private String url;
    private Integer documents;
    private Long pages;
}
