package com.example.nooracoffeeshop.controllers;

import com.example.nooracoffeeshop.services.ManufacturerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManufacturerController {

  @Autowired
  private ManufacturerService manufacturerService;

  // ***** Manufacturer management ******

  @GetMapping("/admin/manufacturer")
  public String displayManufacturer(Model model) {
    model.addAttribute("manufacturers", this.manufacturerService.listAll());
    return "manufacturerManagement";
  }

  @GetMapping("/admin/manufacturer/add")
  public String displayManufacturerAdd(Model model) {
    return "manufacturerAdd";
  }

  @PostMapping("/admin/manufacturer")
  public String addManufacturer(@RequestParam String manufacturerName, @RequestParam String manufacturerUrl) {
    this.manufacturerService.addManufacturer(manufacturerName, manufacturerUrl);
    return "redirect:/admin/manufacturer";
  }

  // update manufacturer

  @GetMapping("/admin/updatemanufacturer/{id}")
  public String showFormForUpdateManufacturer(@PathVariable Long id, Model model) {

    model.addAttribute("manufacturer", this.manufacturerService.getManufacturer(id));

    return "updateManufacturer";

  }

  @PostMapping("/admin/updateManufacturer/{id}")
  public String updateManufacturer(@PathVariable Long id, @RequestParam String manufacturerName,
      @RequestParam String manufacturerUrl) {
    this.manufacturerService.updateManufacturer(id, manufacturerName, manufacturerUrl);

    return "redirect:/admin/manufacturer";

  }

  // Delete_manufacturer

  @GetMapping("/admin/deletemanufacturer/{id}")
  public String deleteManufacturer(@PathVariable Long id) {
    this.manufacturerService.deleteManufacturer(id);
    return "redirect:/admin/manufacturer";
  }

}
