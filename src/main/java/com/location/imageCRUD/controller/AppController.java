package com.location.imageCRUD.controller;

import com.location.imageCRUD.model.Entry;
import com.location.imageCRUD.service.EntryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

  @Autowired
  EntryService entryService;

  @RequestMapping("/")
  public String viewHomePage(Model model) {
    List<Entry> entryList = entryService.listAll();
    model.addAttribute("entryList", entryList);

    return "index";
  }

  @RequestMapping("/new")
  public String newEntryPage(Model model) {
    Entry entry = new Entry();
    model.addAttribute(entry);
    return "new_entry";
  }

  @RequestMapping(value="/save", method=RequestMethod.POST)
  public String saveEntry(@ModelAttribute ("entry") Entry entry) {
    entryService.saveEntry(entry);

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
