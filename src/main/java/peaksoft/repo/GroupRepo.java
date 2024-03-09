package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.GroupResponse;
import peaksoft.entity.Group;

import java.util.List;
@Repository
public interface GroupRepo extends JpaRepository<Group,Long> {
    @Query("select new peaksoft.dto.response.GroupResponse(c.id, c.groupName, c.imageLink, c.description) from Group c")
    List<GroupResponse> findAllGroups();

    @Query("select s.user.firstName, count(s) from Group g join g.students s where g.id = :groupId group by s.id")
    List <String> countById(Long groupId);
}
