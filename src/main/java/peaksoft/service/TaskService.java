package peaksoft.service;

import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {
    HTTPResponse saveToLesson(Long lessonId, TaskRequest taskRequest);

    HTTPResponse update(Long taskId, TaskRequest taskRequest);

    HTTPResponse delete(Long taskId);

    List<TaskResponse> findAll(Long lessonId);

    TaskResponse findById(Long taskId);
}
