package com.location.imageCRUD;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntryRepositoryTests {

  @Autowired
  private EntryRepository entryRepository;

  @Test
  @Order(1)
  public void saveEntryTest() {
    Entry entry = Entry.builder()
        .title("Gauja")
        .location("vidzeme")
        .category("pikniks")
        .image("image")
        .build();

    entryRepository.save(entry);
    assertThat(entry.getId()).isGreaterThan(0);
  }

  @Test
  @Order(2)
  public void getEntryByIdTest() {
    Entry entry = entryRepository.findById(1L).get();
    assertThat(entry.getId()).isEqualTo(1L);
  }

  @Test
  @Order(3)
  public void getListOfEntries() {
    List<Entry> entries = entryRepository.findAll();
    assertThat(entries.size()).isGreaterThan(0);
  }

  @Test
  @Order(4)
  public void updateEntry() {
    Entry entry = entryRepository.findById(1L).get();
    entry.setLocation("Vidzeme");
    Entry entryUpdated = entryRepository.save(entry);
    assertThat(entryUpdated.getLocation()).isEqualTo("Vidzeme");
  }

  @Test
  @Order(5)
  public void deleteEntryTest() {
    Entry entry = entryRepository.findById(1L).get();
    entryRepository.delete(entry);
    Entry entryOne = null;
    Optional<Entry> optionalEntry = entryRepository.findByLocation("Vidzeme");

    if (optionalEntry.isPresent()) {
      entryOne = optionalEntry.get();
    }
    assertThat(entryOne).isNull();
  }

}
