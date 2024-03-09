package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.enums.StudyFormat;
import peaksoft.repo.GroupRepo;
import peaksoft.repo.StudentRepo;
import peaksoft.service.StudentService;


import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepository;
    private final GroupRepo groupRepository;
    private final PasswordEncoder passwordEncoder;

    @Override @Transactional
    public HTTPResponse save(StudentRequest studentRequest) {
        Student student = new Student();
        User user = new User();
        user.setFirstName(studentRequest.firstName());
        user.setLastName(studentRequest.lastName());
        user.setEmail(studentRequest.email());
        user.setPassword(passwordEncoder.encode(studentRequest.password()));
        user.setRole(Role.STUDENT);student.setUser(user);
        student.setPhoneNumber(studentRequest.phoneNumber());
        student.getUser().setEmail(studentRequest.email());
        student.setStudyFormat(studentRequest.studyFormat());
        studentRepository.save(student);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved students !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse asSign(Long groupId, Long studentId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Not found this id !" + groupId));
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            student.setGroup(group);
            group.getStudents().add(student);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully as signed students !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public HTTPResponse deleteById(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id this !" + studentId));
            studentRepository.delete(student);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted students !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override @Transactional
    public HTTPResponse update(Long studentId, StudentRequest studentRequest) {
        try{
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Not found id with :" + studentId));
        student.getUser().setFirstName(studentRequest.firstName());
        student.getUser().setLastName(studentRequest.lastName());
        student.setPhoneNumber(studentRequest.phoneNumber());
        student.getUser().setEmail(studentRequest.email());
        student.setStudyFormat(studentRequest.studyFormat());
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated students !")
                .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<StudentResponse> findAll() {
        return studentRepository.getAll() ;
    }

    @Override
    public List<StudentResponse> sortByFormat(StudyFormat studyFormat) {
        return studentRepository.sortByFormat(studyFormat);
    }

    @Override @Transactional
    public HTTPResponse blockStudent(Long studentId) {
        try {
            Student student = studentRepository.findById(studentId).orElseThrow(() -> new NoSuchElementException("Student with id: " + studentId +"  not found!"));
            boolean block = student.isBlock();
            if (block) {
                student.setBlock(false);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully blocked students !")
                        .build();
            } else {
                student.setBlock(true);
                return HTTPResponse.builder()
                        .httpStatus(HttpStatus.ACCEPTED)
                        .message("Successfully unblocked students !")
                        .build();
            }
        }catch (Exception e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .message(e.getMessage())
                    .build();
        }
    }

}
