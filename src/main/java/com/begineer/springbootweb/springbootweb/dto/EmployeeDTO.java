package com.begineer.springbootweb.springbootweb.dto;

import com.begineer.springbootweb.springbootweb.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Employee name should not be blank")
    @Size(min=3,max=10,message = "Employee Name should be in the range[3,10]")
    private String name;

    @NotBlank(message = "Employee Email should not be blank")
    @Email(message = "Employee Email should be valid Email")
    private String email;

    @NotNull(message ="Employee Age should not be null")
    @Min(value=22,message = "Employee Age should not be less than 22")
    @Max(value=60,message = "Employee Age should not be more than 60 ")
    private Integer age;

    @PastOrPresent(message="DateOfJoining should not be future date")
    private LocalDate date;

    @AssertTrue(message="Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;

    @NotBlank(message = "Employee Role should not be blank")
    //@Pattern(regexp="^(ADMIN|USER)$", message ="Employee Role should be either ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Employee salary should not be null")
    @Positive(message = "Employee salary should not be negative")
    @Digits(integer=6,fraction=2,message = "Employee Salary should be in the form of XXXX.YY")
    @DecimalMax(value="100000.99")
    @DecimalMin(value="1000.99")
    private Double salary;
}
