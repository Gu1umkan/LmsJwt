package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.LessonResponse;
import peaksoft.entity.Lesson;

import java.util.List;

public interface LessonRepo extends JpaRepository<Lesson,Long> {
    @Query("""
select new peaksoft.dto.response.LessonResponse(l.id,l.lessonName)
from Lesson l join l.course ls where ls.id =:courseId
""")
    List<LessonResponse> findAllByCourseId(Long courseId);
}
