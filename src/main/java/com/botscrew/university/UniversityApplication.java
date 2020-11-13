package com.botscrew.university;

import com.botscrew.university.entity.DegreeStat;
import com.botscrew.university.entity.Department;
import com.botscrew.university.entity.Lector;
import com.botscrew.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class UniversityApplication implements CommandLineRunner {

    @Autowired
    UniversityService service;

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String departmentName = "";
        Scanner in = new Scanner(System.in);
        boolean flag = true;

        do {
            System.out.println("\n\n1.Show who is head of department");
            System.out.println("2.Show department statistics");
            System.out.println("3.Show the average salary for the department");
            System.out.println("4.Show count of employee for department");
            System.out.println("5.Global search");
            System.out.println("6.Exit\n");


            int choice = 0;
            try {
                System.out.print("Make your choice: ");
                choice = in.nextInt();
                in.nextLine();
                if(choice <= 0 || choice > 6) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Invalid value type or value is <= 0 or > 6");
                flag = false;
            }

            switch (choice) {
                case 1: {
                    System.out.print("Enter department name: ");
                    departmentName = in.nextLine();
                    Department department = service.findByName(departmentName);
                    System.out.println(department != null ?
                            "Head of " + department.getName() + " department is " + department.getHead() : "This department not exists");
                    break;
                }
                case 2: {
                    System.out.print("Enter department name: ");
                    departmentName = in.nextLine();
                    List<DegreeStat> degreeStats = service.countTotalStatByDegreeName(departmentName);
                    if(degreeStats.isEmpty()) {
                        System.out.println("This department not exists");
                    } else {
                        System.out.println("\n" + departmentName + " statistic:");
                        degreeStats.forEach(degreeStat -> System.out.println(degreeStat.getDegreeName() + " : " + degreeStat.getTotal()));
                    }

                    break;
                }
                case 3: {
                    DecimalFormat df = new DecimalFormat("###.##");
                    System.out.print("Enter department name: ");
                    departmentName = in.nextLine();
                    Double avgSalary = service.getAverageSalaryByDepartmentName(departmentName);
                    System.out.println(avgSalary != null ?
                            "Average salary: " + df.format(avgSalary) : "This department not exists");
                    break;
                }

                case 4: {
                    System.out.print("Enter department name: ");
                    departmentName = in.nextLine();
                    Long count = service.getEmployeesCountByDepartmentName(departmentName);
                    System.out.println(count != 0 ?
                            "Count of employees: " + count : "This department not exists");
                    break;
                }
                case 5: {
                    System.out.print("Enter search template: ");
                    String searchTemplate = in.nextLine();
                    List<Lector> lectors = service.globalSearchByFirstNameOrLastName(searchTemplate);
                    if (lectors.isEmpty()) {
                        System.out.println("Not found any value by template");
                    } else {
                        System.out.println("Search result:");
                        lectors.forEach(lector -> System.out.println(lector.getFirstName() + " " + lector.getLastName()));
                    }

                    break;
                }
                case 6: {
                    flag = false;
                    break;
                }

            }
        } while (flag);

    }
}
