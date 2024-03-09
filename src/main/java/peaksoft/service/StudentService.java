package peaksoft.service;

import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentService {
    HTTPResponse save(StudentRequest studentRequest);

    HTTPResponse asSign(Long groupId, Long studentId);

    HTTPResponse deleteById(Long studentId);

    HTTPResponse update(Long studentId, StudentRequest studentRequest);

    List<StudentResponse> findAll();

    List<StudentResponse> sortByFormat(StudyFormat studyFormat);

    HTTPResponse blockStudent(Long studentId);
}
