package com.example.nooracoffeeshop.controllers;

import com.example.nooracoffeeshop.Repositories.ProductRepository;
import com.example.nooracoffeeshop.services.DepartmentService;
import com.example.nooracoffeeshop.services.ManufacturerService;
import com.example.nooracoffeeshop.services.ProductService;
import com.example.nooracoffeeshop.services.SupplierService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupplierController {

  @Autowired
  private SupplierService supplierService;

  // ***** supplier management ******

  // add supplier & display supplier table
  @GetMapping("/admin/supplier")
  public String displaySupplier(Model model) {
    model.addAttribute("suppliers", this.supplierService.listAll());
    return "supplierManagement";
  }

  @PostMapping("/admin/supplier")
  public String addSupplier(@RequestParam String supplierName, @RequestParam String contactMail,
      @RequestParam String contactName) {
    this.supplierService.addSupplier(supplierName, contactName, contactMail);
    return "redirect:/admin/supplier";
  }

  // update supplier

  @GetMapping("/admin/updatesupplier/{id}")
  public String showFormForUpdateSupplier(@PathVariable Long id, Model model) {

    model.addAttribute("supplier", this.supplierService.getSupplier(id));

    return "updateSupplier";

  }

  @PostMapping("/admin/updatesupplier/{id}")
  public String updateSupplier(@RequestParam String supplierName, @RequestParam String contactMail,
      @RequestParam String contactName, @PathVariable Long id) {

    this.supplierService.updateSupplier(id, supplierName, contactName, contactMail);

    return "redirect:/admin/supplier";

  }

  // Delete_supplier

  @GetMapping("/admin/deletesupplier/{id}")
  public String deleteSupplier(@PathVariable Long id) {
    this.supplierService.deleteSupplier(id);
    return "redirect:/admin/supplier";
  }

}
