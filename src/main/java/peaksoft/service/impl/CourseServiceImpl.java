package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.repo.CompanyRepo;
import peaksoft.repo.CourseRepo;
import peaksoft.service.CourseService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CompanyRepo companyRepository;
    private final CourseRepo courseRepository;

    @Override
    public HTTPResponse save(Long companyId, CourseRequest courseRequest) {
        try {
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Course id with " + companyId + " not found!"));
            Course course = new Course();
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            course.setDateOfStart(courseRequest.dateOfStart());
            course.setCompany(company);
            company.getCourses().add(course);
            courseRepository.save(course);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully save Course !")
                    .build();
        } catch (NoSuchElementException e) {
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    @Transactional
    public HTTPResponse updatedById(Long courseId, CourseRequest courseRequest) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course  id with  " + courseId + " not found !"));
            course.setCourseName(courseRequest.courseName());
            course.setDescription(courseRequest.description());
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated Course !")
                    .build();
        } catch (NoSuchElementException e) {
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<CourseResponse> findAllCourse(Long companyId, String ascOrDesc) {
        if (ascOrDesc.contains("asc")) {
            return courseRepository.sortAsc(companyId);
        } else {
            return courseRepository.sortDesc(companyId);
        }
    }

    @Override
    public HTTPResponse deleteById(Long courseId) {
        try {
            Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Course id  with " + courseId + " not found !"));
            courseRepository.delete(course);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted course")
                    .build();
        } catch (NoSuchElementException e) {
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }
}
