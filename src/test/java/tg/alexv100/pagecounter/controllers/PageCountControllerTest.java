package tg.alexv100.pagecounter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import tg.alexv100.pagecounter.data.RequestEntity.RequestDTO;
import tg.alexv100.pagecounter.data.dto.DocumentsAndPagesCountDTO;
import tg.alexv100.pagecounter.service.PageCountService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class PageCountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PageCountService pageCountService;


    @Test
    public void pageCountControllerTest() throws Exception {
        String url = "C:\\";
        ObjectMapper objectMapper = new ObjectMapper();
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setUrl(url);
        requestDTO.setFormats(new String[]{"pdf"});
        String json = objectMapper.writeValueAsString(requestDTO);
        Mockito.doReturn(new DocumentsAndPagesCountDTO(url, 1, 124L))
                .when(pageCountService)
                .searchDocuments(requestDTO);
        this.mockMvc.perform(
                post("/api/getAll")
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.url").value("C:\\"))
                .andExpect(jsonPath("$.documents").value(1))
                .andExpect(jsonPath("$.pages").value(124));
    }
}