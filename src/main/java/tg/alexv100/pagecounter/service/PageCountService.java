package tg.alexv100.pagecounter.service;

import tg.alexv100.pagecounter.data.RequestEntity.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;

public interface PageCountService {
    DocumentsAndPagesCountDTO searchDocuments(RequestDTO requestObj);
}
