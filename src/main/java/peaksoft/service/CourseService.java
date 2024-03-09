package peaksoft.service;

import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.HTTPResponse;

import java.util.List;

public interface CourseService {
    HTTPResponse save(Long companyId, CourseRequest courseRequest);

    HTTPResponse updatedById(Long companyId,CourseRequest courseRequest);

    List<CourseResponse> findAllCourse(Long companyId, String ascOrDesc);

    HTTPResponse deleteById(Long courseId);
}
