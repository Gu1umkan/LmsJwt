package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CourseResponse;
import peaksoft.entity.Course;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course,Long> {
    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId")
    List<CourseResponse> findAllByID(Long companyId);

    @Query("select c from Course c where c.id in (:courseIds)")
    List<Course> findCoursesWithIds(List<Long> courseIds);

    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart desc ")
    List<CourseResponse> sortDesc(Long companyId);
    @Query("select new peaksoft.dto.response.CourseResponse(c.id,c.courseName,c.dateOfStart, c.description) from Course c where c.company.id = :companyId order by c.dateOfStart  ")
    List<CourseResponse> sortAsc(Long companyId);
}
