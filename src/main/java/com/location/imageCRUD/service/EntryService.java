package com.location.imageCRUD.service;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

// try - catch vs "throws exception" ????

@Service
public class EntryService {

  @Autowired
  private EntryRepository entryRepository;

  public Entry getEntryById(Long id) {
    return entryRepository.findById(id).get();
  }

  public void deleteEntry(Long id) {
    entryRepository.deleteById(id);
  }

  public void saveEntry(MultipartFile file, String name, String location, String category) {

    Entry entry = new Entry();
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    // entry.setImage() :
    try {
      entry.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
    }
    catch (IOException e) {
      e.printStackTrace();
    }

    entry.setTitle(name);
    entry.setLocation(location);
    entry.setCategory(category);

    entryRepository.save(entry);
  }

  public List<Entry> listAll (String keyword) {
    if (keyword != null) {
      return entryRepository.findAll(keyword);
    }
    return entryRepository.findAll();
  }

}
