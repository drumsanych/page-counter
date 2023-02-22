package tg.alexv100.pagecounter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tg.alexv100.pagecounter.data.RequestEntity.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.service.PageCountService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PageCountController {

    private final PageCountService pageCountService;

    @PostMapping("/getAll")
    public ResponseEntity<DocumentsAndPagesCountDTO> getDocumentsAndPagesCountDTO(@RequestBody RequestDTO requestObj) {
        if (requestObj.getUrl() == null) throw new RuntimeException("Url field is null");
        if (requestObj.getFormats() == null || requestObj.getFormats().length == 0) {
            throw new RuntimeException("Need one file format at least! Add field 'formats' and value as : ['pdf']");
        }
        return new ResponseEntity<>(pageCountService.searchDocuments(requestObj), HttpStatusCode.valueOf(200));
    }
}
