package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.repo.LessonRepo;
import peaksoft.repo.TaskRepo;
import peaksoft.service.TaskService;

import java.util.List;
import java.util.NoSuchElementException;
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepository;
    private final LessonRepo lessonRepository;
    @Override
    public HTTPResponse saveToLesson(Long lessonId, TaskRequest taskRequest) {
        try {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(() -> new NoSuchElementException("Not found !"));
            Task task = new Task();
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadLine(taskRequest.deadLine());

            task.setLesson(lesson);
            taskRepository.save(task);

            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully save task !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override @Transactional
    public HTTPResponse update(Long taskId, TaskRequest taskRequest) {
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Not found this id " + taskId));
            task.setTaskName(taskRequest.taskName());
            task.setTaskText(taskRequest.taskText());
            task.setDeadLine(taskRequest.deadLine());
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully updated task !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public HTTPResponse delete(Long taskId) {
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Not found this id " + taskId));
            taskRepository.delete(task);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted task !")
                    .build();
        }catch (NoSuchElementException e){
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public List<TaskResponse> findAll(Long lessonId) {
        return taskRepository.findAllLessonId(lessonId);
    }

    @Override
    public TaskResponse findById(Long taskId) {
        return taskRepository.findByTaskId(taskId);
    }
}
