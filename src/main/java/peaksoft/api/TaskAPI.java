package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.TaskRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.TaskResponse;
import peaksoft.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskAPI {
    private final TaskService taskService;

    @Secured({"INSTRUCTOR"})
    @PostMapping("/save/{lessonId}")
    public HTTPResponse save(@PathVariable Long lessonId, @RequestBody TaskRequest taskRequest){
        return taskService.saveToLesson(lessonId,taskRequest);
    }

    @Secured({"INSTRUCTOR"})
    @PostMapping("/update/{taskId}")
    public HTTPResponse update(@PathVariable Long taskId,@RequestBody TaskRequest taskRequest){
        return taskService.update(taskId,taskRequest);
    }
    @Secured({"INSTRUCTOR"})
    @PostMapping("delete/{taskId}")
    public HTTPResponse delete(@PathVariable Long taskId){
        return taskService.delete(taskId);
    }

    @Secured({"INSTRUCTOR"})
    @GetMapping("all/{lessonId}")
    public List<TaskResponse> findAll(@PathVariable Long lessonId){
        return taskService.findAll(lessonId);
    }
    @Secured({"INSTRUCTOR"})
    @GetMapping("find/{taskId}")
    public TaskResponse findById(@PathVariable Long taskId){
        return taskService.findById(taskId);
    }
}
