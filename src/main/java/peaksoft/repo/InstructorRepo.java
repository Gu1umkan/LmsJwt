package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.InfoInstructorsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.entity.Instructor;

import java.util.List;
@Repository
public interface InstructorRepo extends JpaRepository<Instructor,Long> {


    @Query("SELECT new peaksoft.dto.response.InstructorResponse(i.id, i.user.firstName, i.user.lastName, i.phoneNumber,i.specialization) " +
            "FROM Instructor i " +
            "JOIN i.companies c " +
            "WHERE c.id = :companyId")
    List<InstructorResponse> findAllId(Long companyId);


    @Query("select count(s) from Instructor i join i.courses c join c.groups g join g.students s where i.id = :inId")
    Integer countOfById(Long inId);



    @Query("""
select new peaksoft.dto.response.InfoInstructorsResponse(i.id,i.user.firstName,i.user.lastName,i.phoneNumber,i.specialization) from Instructor i where i.id =:instructorId
""")
    InfoInstructorsResponse fullInstructorInfos(Long instructorId);
}
