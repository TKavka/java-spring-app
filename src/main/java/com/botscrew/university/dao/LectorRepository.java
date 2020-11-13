package com.botscrew.university.dao;

import com.botscrew.university.entity.DegreeStat;
import com.botscrew.university.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {



    @Query(value = "SELECT degree_name AS degreeName, COUNT(degree_name) AS total " +
            "FROM lector " +
            "LEFT JOIN lector_department AS ld " +
            "ON lector.id = ld.lector_id " +
            "LEFT JOIN department " +
            "ON ld.department_id = department.id " +
            "WHERE department.name = ?1 " +
            "GROUP BY degree_name", nativeQuery = true)
    List<DegreeStat> countTotalStatByDegreeName(String departmentName);

    @Query(value = "SELECT AVG(salary) " +
            "FROM lector " +
            "LEFT JOIN lector_department AS ld " +
            "ON lector.id = ld.lector_id " +
            "LEFT JOIN department " +
            "ON ld.department_id = department.id " +
            "WHERE department.name = ?1", nativeQuery = true)
    Double getAverageSalaryByDepartmentName(String departmentName);

    @Query(value = "SELECT COUNT(*) " +
            "FROM lector " +
            "LEFT JOIN lector_department AS ld " +
            "ON lector.id = ld.lector_id " +
            "LEFT JOIN department " +
            "ON ld.department_id = department.id " +
            "WHERE department.name=?1", nativeQuery = true)
    Long getEmployeesCountByDepartmentName(String departmentName);

    @Query(value = "SELECT lector.id, first_name, last_name, salary, degree_name " +
            "FROM lector " +
            "LEFT JOIN lector_department AS ld " +
            "ON lector.id = ld.lector_id " +
            "LEFT JOIN department " +
            "ON ld.department_id = department.id " +
            "WHERE first_name RLIKE ?1 " +
            "OR last_name RLIKE ?1 " +
            "GROUP BY id", nativeQuery = true)
    List<Lector> globalSearchByFirstNameOrLastName(String searchTemplate);
}
