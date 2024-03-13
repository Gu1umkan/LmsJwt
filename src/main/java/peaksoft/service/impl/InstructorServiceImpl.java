package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtService;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.InfoInstructorsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.dto.response.SignResponse;
import peaksoft.entity.*;
import peaksoft.enums.Role;
import peaksoft.exceptions.MyException;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.InstructorRepo;
import peaksoft.repo.UserRepo;
import peaksoft.service.InstructorService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private final UserRepo userRepo;
    private final InstructorRepo instructorRepo;
    private final CompanyRepo companyRepository;
    private final CourseRepo courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public SignResponse signUp(InstructorRequest instructorRequest) {

        try {
            boolean exists = userRepo.existsByEmail(instructorRequest.email());
            if (exists) throw new MyException("Email : " + instructorRequest.email() + " already exist");

//            Instructor instructor = new Instructor();
            User user = new User();

            user.setFirstName(instructorRequest.firstName());
            user.setLastName(instructorRequest.lastName());
            user.setEmail(instructorRequest.email());
            user.setPassword(passwordEncoder.encode(instructorRequest.password()));
            user.setRole(Role.INSTRUCTOR);
            instructorRepo.save(Instructor.builder()
                            .user(user)
                            .phoneNumber(instructorRequest.phoneNumber())
                            .specialization(instructorRequest.specialization())
                            .build());
//            instructor.setUser(user);
//            instructor.setPhoneNumber(instructorRequest.phoneNumber());
//            instructor.setSpecialization(instructorRequest.specialization());

            userRepo.save(user);

            String newToken = jwtService.createToken(user);
            return SignResponse.builder()
                    .token(newToken)
                    .id(user.getId())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .message("Successfully registered!")
                    .build();

        }catch (RuntimeException | MyException e){
            return SignResponse.builder()
                    .message(e.getMessage())
                    .build();
        }

    }

    @Override
    public HTTPResponse save(InstructorRequest instructorRequest) {

        Instructor instructor = new Instructor();
        User user = new User();
        user.setFirstName(instructorRequest.firstName());
        user.setLastName(instructorRequest.lastName());
        user.setEmail(instructorRequest.email());
        user.setPassword(passwordEncoder.encode(instructorRequest.password()));
        user.setRole(Role.INSTRUCTOR);
        instructor.setUser(user);
        instructor.setPhoneNumber(instructorRequest.phoneNumber());
        instructor.setSpecialization(instructorRequest.specialization());
        instructorRepo.save(instructor);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved instructor !")
                .build();
    }

    @Override
    @Transactional
    public HTTPResponse assign(Long companyId, Long instructorId) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with " + companyId + "not found"));
            Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
            company.getInstructors().add(instructor);
            instructor.getCompanies().add(company);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully assigned !")
                    .build();
        } catch (EntityNotFoundException e) {
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public HTTPResponse update(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        instructor.getUser().setFirstName(instructorRequest.firstName());
        instructor.getUser().setLastName(instructorRequest.lastName());
        instructor.setPhoneNumber(instructorRequest.phoneNumber());
        instructor.setSpecialization(instructorRequest.specialization());

        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated !")
                .build();
    }

    @Override
    @Transactional
    public HTTPResponse delete(Long instructorId) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
        instructorRepo.delete(instructor);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted !")
                .build();
    }

    @Override
    public List<InstructorResponse> findAll(Long companyId) {
        return instructorRepo.findAllId(companyId);
    }

    @Override
    public Integer countStudent(Long inId) {
        return instructorRepo.countOfById(inId);
    }

    @Override
    @Transactional
    public HTTPResponse asSignCourse(Long courseId, Long instructorId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Not found" + courseId));
            Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(() -> new NoSuchElementException("Not found"));
            course.setInstructor(instructor);
            instructor.getCourses().add(course);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully asSigned !")
                    .build();
        } catch (NoSuchElementException e) {
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public InfoInstructorsResponse infoInstructor(Long instructorId) {
        Instructor instructor = instructorRepo.findById(instructorId).orElseThrow(
                () -> new NoSuchElementException("Instructor with id: " + instructorId + " not found!"));
        InfoInstructorsResponse infoInstructorsResponse = instructorRepo.fullInstructorInfos(instructorId);
        List<Course> courses = instructor.getCourses();
        for (Course course : courses) {
            for (Group group : course.getGroups()) {
                infoInstructorsResponse.groupsNameWithStudent.put(group.getGroupName(), group.getStudents().size());
            }
        }
        return infoInstructorsResponse;
    }

}
