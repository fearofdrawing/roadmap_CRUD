package com.location.imageCRUD.controller;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.service.EntryService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

  @Autowired
  EntryService entryService;

  @RequestMapping("/")
  public String viewHomePage(Model model, @Param("keyword") String keyword) {
    List<Entry> entryList = entryService.listAll(keyword);
    model.addAttribute("entryList", entryList);
    model.addAttribute("keyword", keyword);

    return "index";
  }

  @RequestMapping("/new")
  public String newEntryPage(Model model) {
    Entry entry = new Entry();
    model.addAttribute(entry);
    return "new_entry";
  }

  @PostMapping("/save")
  public String saveEntry(
      @RequestParam ("image") MultipartFile file,
      @RequestParam ("name") String name,
      @RequestParam ("location") String location,
      @RequestParam ("category") String category
      ) {
    entryService.saveEntry(file, name, location, category);

    return "redirect:/";
  }

  @RequestMapping("edit/{id}")
  public ModelAndView showEditEntryPage(@PathVariable (name="id") Long id) {
    ModelAndView modelAndView = new ModelAndView("edit_entry");
    Entry entry = entryService.getEntryById(id);
    modelAndView.addObject("entry", entry);

    return modelAndView;
  }

  @RequestMapping("delete/{id}")
  public String deleteEntryPage(@PathVariable (name="id") Long id) {
    entryService.deleteEntry(id);

    return "redirect:/";
  }

}
