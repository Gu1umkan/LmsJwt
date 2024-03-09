package peaksoft.service;

import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.LessonResponse;

import java.util.List;

public interface LessonService {
    HTTPResponse save(Long courseId, LessonRequest lessonRequest);

    HTTPResponse update(Long lessonId, LessonRequest lessonRequest);

    HTTPResponse deleteById(Long lessonId);

    List<LessonResponse> findAll(Long courseId);
}
