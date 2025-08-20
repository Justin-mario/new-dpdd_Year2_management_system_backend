package com.mkcollege.dpdd_students_management_system.repository;

import com.mkcollege.dpdd_students_management_system.data.dto.StudentDetailsDto;
import com.mkcollege.dpdd_students_management_system.data.entity.StudentDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class StudentDetailsRepositoryTest {
    @Autowired
    private  StudentDetailsRepository studentDetailsRepository;


    @Test
    void dateBaseEmptyWhenNoDataEntered() {
      List<StudentDetails> allStudents = studentDetailsRepository.findAll();
      assertTrue(allStudents.isEmpty());
    }

    @Test
    void studentDetailsCanBeAddedToDatabase() {
        StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
        studentDetailsDto.setFirstName("John");
        studentDetailsDto.setLastName("Doe");
        studentDetailsDto.setStudentNumber(12344L);
        studentDetailsDto.setDob("12/4/1998");
        studentDetailsDto.setParentPhone1("1234567");
        studentDetailsDto.setParentPhone2("987654321");
        studentDetailsDto.setParentEmail("john@doe.com");
        StudentDetails studentDetails = new StudentDetails(studentDetailsDto);
        studentDetailsRepository.save(studentDetails);
        List<StudentDetails> allStudents = studentDetailsRepository.findAll();
        assertEquals(1,allStudents.size());

    }

    @Test
    void multipleStudentDetailsCanBeAddedToDatabase() {
        StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
        studentDetailsDto.setFirstName("John");
        studentDetailsDto.setLastName("Doe");
        studentDetailsDto.setStudentNumber(12344L);
        studentDetailsDto.setDob("12/4/1998");
        studentDetailsDto.setParentPhone1("1234567");
        studentDetailsDto.setParentPhone2("987654321");
        studentDetailsDto.setParentEmail("john@doe.com");

        StudentDetails studentDetails = new StudentDetails(studentDetailsDto);

        StudentDetailsDto studentDetailsDto1 = new StudentDetailsDto();
        studentDetailsDto1.setFirstName("Ben");
        studentDetailsDto1.setLastName("Dowson");
        studentDetailsDto1.setStudentNumber(123L);
        studentDetailsDto1.setDob("12/4/1999");
        studentDetailsDto1.setParentPhone1("12345647");
        studentDetailsDto1.setParentPhone2("9876543241");
        studentDetailsDto1.setParentEmail("ben@doe.com");
        StudentDetails studentDetails1 = new StudentDetails(studentDetailsDto);
        studentDetailsRepository.save(studentDetails1);
        studentDetailsRepository.save(studentDetails);

        List<StudentDetails> allStudents = studentDetailsRepository.findAll();
        assertEquals(2,allStudents.size());

    }
}