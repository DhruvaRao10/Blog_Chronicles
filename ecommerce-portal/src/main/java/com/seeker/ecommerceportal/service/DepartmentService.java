package com.seeker.ecommerceportal.service;

import com.seeker.ecommerceportal.entity.Department;
import com.seeker.ecommerceportal.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department department);

    public Department fetchDepartmentByNameIgnoreCase(String departmentName);
}
