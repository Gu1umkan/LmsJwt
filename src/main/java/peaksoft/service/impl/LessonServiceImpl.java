package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.LessonRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Lesson;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.LessonRepo;
import peaksoft.service.LessonService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepo lessonRepository;
    private final CourseRepo courseRepository;
    @Override
    public HTTPResponse save(Long courseId, LessonRequest lessonRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NoSuchElementException("Not found" + courseId));
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.lessonName());
        lesson.setCourse(course);
        lessonRepository.save(lesson);
        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved Lesson !")
                .build();
    }

    @Override @Transactional
    public HTTPResponse update(Long lessonId, LessonRequest lessonRequest) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
            lesson.setLessonName(lessonRequest.lessonName());
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated Lesson !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public HTTPResponse deleteById(Long lessonId) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
            lessonRepository.delete(lesson);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted Lesson !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<LessonResponse> findAll(Long courseId) {
        return lessonRepository.findAllByCourseId(courseId);
    }


}
