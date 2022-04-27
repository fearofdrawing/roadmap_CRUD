package com.location.imageCRUD;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import com.location.imageCRUD.service.EntryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

@SpringBootTest
public class EntryServiceTests {

  MockMultipartFile testFile = new MockMultipartFile("file", "test", null, "test-file".getBytes());

  @Autowired
  private EntryService entryService;

  @MockBean
  private EntryRepository entryRepository;

  @Test
  void saveEntryTest() {

    Entry entry = new Entry();

    Long id = entry.getId();
    String testTitle = "Gauja";
    String testLocation = "Vidzeme";
    String testCategory = "pikniks";

    entry.setTitle(testTitle);
    entry.setLocation(testLocation);
    entry.setCategory(testCategory);

    entryService.saveEntry(id, testFile, "Gauja", "Vidzeme", "pikniks");
    assertThat(entry.getLocation()).isEqualTo(testLocation);
    assertThat(entry.getTitle()).isEqualTo(testTitle);
    assertThat(entry.getLocation()).isEqualTo(testLocation);
    assertThat(entry.getCategory()).isEqualTo(testCategory);
  }

  @Test
  void listAllWithKeywordTest() {
    String keyword = "pikniks";
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

    List<Entry> testList = new ArrayList<>();
    testList.add(entryOne);
    testList.add(entryTwo);

    when(entryRepository.findAll()).thenReturn(testList);
    assertThat(entryService.listAll(keyword)).size().isLessThan(testList.size());

  }

  @Test
  void listAllWithNoKeyword() {

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

    List<Entry> testList = new ArrayList<>();
    testList.add(entryOne);
    testList.add(entryTwo);

    when(entryRepository.findAll()).thenReturn(testList);
    assertThat(entryService.listAll(null)).size().isEqualTo(testList.size());
  }

  @Test
  void getEntryByIdTest() {

    Entry entry = Entry.builder()
        .title("Gauja")
        .location("Vidzeme")
        .category("pikniks")
        .image("image")
        .build();

    when(entryRepository.findById(1L)).thenReturn(Optional.ofNullable(entry));
    assertThat(entryService.getEntryById(1L)).isEqualTo(entry);
  }

}
