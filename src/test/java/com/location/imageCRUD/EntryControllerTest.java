package com.location.imageCRUD;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import com.location.imageCRUD.service.EntryService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EntryControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private EntryService entryService;

  @MockBean
  private EntryRepository entryRepository;

  @Test
  void viewHomePageTest() throws Exception {

    List<Entry> entryList = new ArrayList<>();
    Entry entryOne = Entry.builder()
        .title("Gauja")
        .location("Vidzeme")
        .category("pikniks")
        .image("image")
        .build();

    Entry entryTwo = Entry.builder()
        .title("Festivāls")
        .location("Roja")
        .category("kino")
        .image("image")
        .build();

    entryList.add(entryOne);
    entryList.add(entryTwo);

    Mockito.when(entryService.listAll(null)).thenReturn(entryList);
    String url = "/";
    mockMvc.perform(get(url)).andExpect(status().isOk());
  }

  @Test
  void newEntryPageTest() throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/new"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.view().name("new_entry"));
  }

  @Test
  void showEditEntryPage() {

  }

}
