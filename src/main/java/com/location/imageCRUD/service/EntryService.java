package com.location.imageCRUD.service;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntryService {

  @Autowired
  private EntryRepository entryRepository;

  public List<Entry> listAll() {
    return entryRepository.findAll();
  }

  public Entry getEntryById(Long id) {
    return entryRepository.findById(id).get();
  }

  public void deleteEntry(Long id) {
    entryRepository.deleteById(id);
  }

  public void saveEntry(Entry entry) {
    entryRepository.save(entry);
  }
}
