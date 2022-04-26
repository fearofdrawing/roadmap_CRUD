package com.location.imageCRUD;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageCrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ImageCrudApplication.class, args);
	}

	@Autowired
	private EntryRepository entryRepository;

	@Override
	public void run(String... args) throws Exception {

		Entry entry1 = new Entry("Cīrava", "Kurzeme", "kebabs");
		entryRepository.save(entry1);
		Entry entry2 = new Entry("Pāvilosta", "Kurzeme", "jūra");
		entryRepository.save(entry2);
		Entry entry3 = new Entry("Driškins", "LV", "kempings");
		//ntryRepository.save(entry3);
		Entry entry4 = new Entry("Imanta", "Rīga", "māksla");
		//entryRepository.save(entry4);

	}
}
