package com.begineer.springbootweb.springbootweb.dto;

import java.time.LocalDate;

public class EmployeeDTO {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    private LocalDate date;

    private Boolean isActive;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Long id, String name,String email,Integer age,LocalDate date, Boolean isActive ) {
        this.id = id;
        this.isActive = isActive;
        this.date = date;
        this.age = age;
        this.email = email;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
