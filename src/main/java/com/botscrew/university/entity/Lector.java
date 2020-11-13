package com.botscrew.university.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lector")
public class Lector {

    @ManyToMany
    @JoinTable(
            name = "lector_department",
            joinColumns = @JoinColumn(name = "lectorID"),
            inverseJoinColumns = @JoinColumn(name = "departmentID")
    )
    private List<Department> departments;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "degree_name")
    private String degreeName;

    @Column(name = "salary")
    private Integer salary;

    public Lector() {
    }


    public Lector(String firstName, String lastName, String degreeName, Integer salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.degreeName = degreeName;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDegreeName() {
        return degreeName;
    }

    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", degreeName='" + degreeName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
