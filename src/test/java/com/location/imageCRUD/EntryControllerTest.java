package com.location.imageCRUD;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.location.imageCRUD.service.EntryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EntryControllerTest {

  @Autowired
  private MockMvc mockMv;

  @MockBean
  private EntryService entryService;

  @Test
  @DisplayName("Test to upload a single file")
  void shouldUploadImage() throws Exception {
    MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "test", null, "test-file".getBytes());
    this.mockMv.perform(multipart("/new").file(mockMultipartFile))
        .andExpect(status().isOk());
  }

}
