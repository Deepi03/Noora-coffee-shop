package com.example.nooracoffeeshop.services;

import java.util.List;

import com.example.nooracoffeeshop.Repositories.SupplierRepository;
import com.example.nooracoffeeshop.model.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  private SupplierRepository supplierRepository;

  public List<Supplier> listAll(){
      return supplierRepository.findAll();
  }

  public Supplier getSupplier(Long id){
      return supplierRepository.getById(id);
  }


  public void addSupplier(String supplierName,String contactName,String contactMail){
    Supplier supplier = new Supplier();
    supplier.setName(supplierName);
    supplier.setContactPersonEmail(contactMail);
    supplier.setContactPersonName(contactName);
    supplierRepository.save(supplier);
  }


  public void updateSupplier(Long id,String supplierName,String contactName,String contactMail){

    Supplier existingSupplier = supplierRepository.getById(id);
      existingSupplier.setContactPersonEmail(contactMail);
      existingSupplier.setContactPersonName(contactName);
      existingSupplier.setName(supplierName);

      supplierRepository.save(existingSupplier);

  }


  public void deleteSupplier(Long id){
      supplierRepository.deleteById(id);
  }


    
}
