package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.StudentResponse;
import peaksoft.entity.Student;
import peaksoft.enums.StudyFormat;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    @Query("select new peaksoft.dto.response.StudentResponse(c.id,c.user.firstName,c.user.lastName,c.phoneNumber,c.user.email, c.studyFormat) from Student  c")
    List<StudentResponse> getAll();
    @Query("select new peaksoft.dto.response.StudentResponse(c.id,c.user.firstName,c.user.lastName,c.phoneNumber,c.user.email, c.studyFormat) from Student c where c.studyFormat =:studyFormat")
    List<StudentResponse> sortByFormat(StudyFormat studyFormat);
}
