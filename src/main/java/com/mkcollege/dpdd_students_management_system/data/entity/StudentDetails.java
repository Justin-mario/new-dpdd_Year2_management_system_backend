package com.mkcollege.dpdd_students_management_system.data.entity;


import com.mkcollege.dpdd_students_management_system.data.dto.StudentDetailsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentDetails {

    @Id
    private Long studentNumber;
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Email(message="invalid email format")
    @NotBlank
    @Column(nullable = false)
    private String parentEmail;

    @Column(nullable = false)
    private String parentPhone1;

    @Column(nullable = true)
    private String parentPhone2;

    @Column(nullable = true)
    private String projectedGrade;

    @Column(nullable = false)
    private String dob;

    public StudentDetails(StudentDetailsDto studentDetailsDto) {
        this.studentNumber = studentDetailsDto.getStudentNumber();
        this.firstName = studentDetailsDto.getFirstName();
        this.lastName = studentDetailsDto.getLastName();
        this.parentEmail = studentDetailsDto.getParentEmail();
        this.parentPhone1 = studentDetailsDto.getParentPhone1();
        this.parentPhone2 = studentDetailsDto.getParentPhone2();
        this.projectedGrade = studentDetailsDto.getProjectedGrade();
        this.dob = studentDetailsDto.getDob();


    }
}
