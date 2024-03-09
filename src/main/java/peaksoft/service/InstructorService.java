package peaksoft.service;

import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.InfoInstructorsResponse;
import peaksoft.dto.response.InstructorResponse;

import java.util.List;

public interface InstructorService {
    HTTPResponse save(InstructorRequest instructorRequest);

    HTTPResponse assign(Long companyId, Long instructorId);

    HTTPResponse update(Long instructorId, InstructorRequest instructorRequest);

    HTTPResponse delete(Long instructorId);

    List<InstructorResponse> findAll(Long companyId);

    Integer countStudent(Long inId);

    HTTPResponse asSignCourse(Long courseId, Long instructorId);

    InfoInstructorsResponse infoInstructor(Long instructorId);
}
