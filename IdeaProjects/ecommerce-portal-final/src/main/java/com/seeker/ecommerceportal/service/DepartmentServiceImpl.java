package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.Repository.DepartmentRepository;
import com.seeker.ecommerceportal.entity.Department;
import com.seeker.ecommerceportal.error.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements  DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department =  departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department Not Found ");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
         departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department depDB = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getDepartmentName()) &&
                (!"".equalsIgnoreCase(department.getDepartmentName())) ){
            depDB.setDepartmentName(department.getDepartmentName());
        }
        if(Objects.nonNull(department.getDepartmentAddress()) &&
                (!"".equalsIgnoreCase(department.getDepartmentAddress())) ){
            depDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        if(Objects.nonNull(department.getDepartmentCode()) &&
                (!"".equalsIgnoreCase(department.getDepartmentCode())) ){
            depDB.setDepartmentName(department.getDepartmentCode());
        }
        return departmentRepository.save(depDB);
    }


    @Override
    public Department fetchDepartmentByNameIgnoreCase(String departmentName){
        return departmentRepository.findBydepartmentNameIgnoreCase(departmentName);
    }
}
