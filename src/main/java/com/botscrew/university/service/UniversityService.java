package com.botscrew.university.service;


import com.botscrew.university.dao.DepartmentRepository;
import com.botscrew.university.dao.LectorRepository;
import com.botscrew.university.entity.DegreeStat;
import com.botscrew.university.entity.Department;
import com.botscrew.university.entity.Lector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    LectorRepository lectorRepository;

    @Transactional
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Transactional
    public List<DegreeStat> countTotalStatByDegreeName(String departmentName) {
        return lectorRepository.countTotalStatByDegreeName(departmentName);
    }

    @Transactional
    public Double getAverageSalaryByDepartmentName(String departmentName) {
        return lectorRepository.getAverageSalaryByDepartmentName(departmentName);
    }

    @Transactional
    public Long getEmployeesCountByDepartmentName(String departmentName) {
        return lectorRepository.getEmployeesCountByDepartmentName(departmentName);
    }

    @Transactional
    public List<Lector> globalSearchByFirstNameOrLastName(String searchTemplate) {
        return lectorRepository.globalSearchByFirstNameOrLastName(searchTemplate);
    }
}
