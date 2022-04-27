package com.location.imageCRUD.service;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EntryService {


  @Autowired
  private EntryRepository entryRepository;

  public Entry saveEntry(MultipartFile file, String title, String location, String category) {

    Entry entry = new Entry();
    /*Long id = entry.getId();
    if (entryRepository.existsById(id)) {
      entry.setId(id);
    }*/

    try {
      entry.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    entry.setTitle(title);
    entry.setLocation(location);
    entry.setCategory(category);

    return entryRepository.save(entry);
  }

  public Entry updateEntry(Long id, MultipartFile file, String title, String location, String category) {

    Entry entry = new Entry();

    if (entryRepository.existsById(id)) {
      entry.setId(id);
    }

    try {
      entry.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    entry.setTitle(title);
    entry.setLocation(location);
    entry.setCategory(category);

    return entryRepository.save(entry);
  }

  public List<Entry> listAll (String keyword) {
    if (keyword != null) {
      return entryRepository.findAll(keyword);
    }
    return entryRepository.findAll();
  }

  public Entry getEntryById(Long id) {
    return entryRepository.findById(id).get();
  }

  public void deleteEntry(Long id) {
    entryRepository.deleteById(id);
  }

}
