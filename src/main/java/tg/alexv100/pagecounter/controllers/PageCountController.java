package tg.alexv100.pagecounter.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.service.interfaces.PageCountService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PageCountController {

    private final PageCountService pageCountService;

    @PostMapping("/getAll")
    public ResponseEntity<DocumentsAndPagesCountDTO> getDocumentsAndPagesCountDTO(String url) {
        return null; //todo реализовать сервисный слой
    }

}
