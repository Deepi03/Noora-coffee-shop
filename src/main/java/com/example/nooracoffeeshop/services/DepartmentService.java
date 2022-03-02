package com.example.nooracoffeeshop.services;

import java.util.List;

import com.example.nooracoffeeshop.Repositories.DepartmentRepository;
import com.example.nooracoffeeshop.model.Department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {


    @Autowired
    public DepartmentRepository departmentRepository;


    public List<Department> listAll(){
        return departmentRepository.findAll();
    }

    

    public Department getDepartment(Long id){
        return departmentRepository.getById(id);
    }
}
