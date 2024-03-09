package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.TaskResponse;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskRepo extends JpaRepository<Task,Long> {
    @Query("""
    select new peaksoft.dto.response.TaskResponse(l.id,l.taskName,l.taskText,l.deadLine)
    from Task l join l.lesson ls where ls.id =:lessonId
    """)
    List<TaskResponse> findAllLessonId(Long lessonId);

    @Query("""
    select new peaksoft.dto.response.TaskResponse(l.id,l.taskName,l.taskText,l.deadLine)
    from Task l where l.id =:taskId
    """)
    TaskResponse findByTaskId(Long taskId);
}
