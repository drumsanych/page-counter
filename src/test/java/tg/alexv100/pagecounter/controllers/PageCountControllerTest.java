package tg.alexv100.pagecounter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tg.alexv100.pagecounter.data.requestDto.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.service.PageCountService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PageCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PageCountService pageCountService;


    @Test
    public void nonNullFields_Request_ok() throws Exception {
        String url = "C:\\";

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl(url);
        requestDTO.setFormats(new String[]{"pdf"});

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestDTO);

        Mockito.doReturn(new DocumentsAndPagesCountDTO(url, 1, 124L))
                .when(pageCountService)
                .searchDocuments(requestDTO);

        this.mockMvc.perform(
                post("/api/getDocumentsCount")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("C:\\"))
                .andExpect(jsonPath("$.documents").value(1))
                .andExpect(jsonPath("$.pages").value(124));
    }

    @Test
    public void emptyFormatsArray_Request_ExceptionJSON() throws Exception {
        String url = "C:\\";

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl(url);
        requestDTO.setFormats(new String[0]);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestDTO);
        this.mockMvc.perform(
                        post("/api/getDocumentsCount")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().is(210))
                .andExpect(jsonPath("$.message")
                        .value("Need one file format at least! Add field 'formats' and value as : ['pdf']"));
    }

    @Test
    public void nullFieldFormatsArray_Request_ExceptionJSON() throws Exception {
        String url = "C:\\";

        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl(url);
        requestDTO.setFormats(null);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestDTO);

        this.mockMvc.perform(
                        post("/api/getDocumentsCount")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().is(210))
                .andExpect(jsonPath("$.message")
                        .value("Need one file format at least! Add field 'formats' and value as : ['pdf']"));
    }


    @Test
    public void nullFieldUrl_Request_ExceptionJSON() throws Exception {
        RequestDTO requestDTO = new RequestDTO();

        requestDTO.setUrl(null);
        requestDTO.setFormats(new String[] {"pdf"});

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestDTO);

        mockMvc.perform(
                        post("/api/getDocumentsCount")
                                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().is(210))
                .andExpect(jsonPath("$.message")
                        .value("Url field is null"));
    }
}