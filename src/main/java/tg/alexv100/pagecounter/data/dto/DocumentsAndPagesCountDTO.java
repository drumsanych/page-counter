package tg.alexv100.pagecounter.data.dto;

import lombok.Data;

@Data
public class DocumentsAndPagesCountDTO {
    private String url;
    private Integer documentsCount;
    private Long pagesCount;
}
