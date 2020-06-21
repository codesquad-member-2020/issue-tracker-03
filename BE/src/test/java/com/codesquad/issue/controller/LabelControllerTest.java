package com.codesquad.issue.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.codesquad.issue.domain.label.request.LabelCreateRequest;
import com.codesquad.issue.service.LabelService;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(LabelController.class)
@MockBean(JpaMetamodelMappingContext.class)
class LabelControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LabelService labelService;

    @Test
    @DisplayName("잘못된 라벨 생성 요청")
    void wrong_label_creation_request() throws Exception {
        LabelCreateRequest request = new LabelCreateRequest();
        request.setName("");
        request.setDescription("");
        request.setColor("");

        Gson gson = new Gson();
        String jsonString = gson.toJson(request);

        mockMvc.perform(post("/labels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
