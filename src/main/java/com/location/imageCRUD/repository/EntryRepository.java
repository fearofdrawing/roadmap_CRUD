package com.location.imageCRUD.repository;

import com.location.imageCRUD.model.Entry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

  @Query("SELECT e FROM Entry e WHERE"
  + " CONCAT(e.title, e.location, e.category)"
  + " LIKE %?1%")
 List<Entry> findAll(String keyword);

}



