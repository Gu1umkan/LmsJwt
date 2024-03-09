package peaksoft.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.repo.CourseRepo;
import peaksoft.repo.GroupRepo;
import peaksoft.service.GroupService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepository;
    private final CourseRepo courseRepository;

    @Override
    public HTTPResponse save(GroupRequest groupRequest) {
        try {
            List<Long> courseIds = groupRequest.coursesIds();
            List<Course> courses = courseRepository.findCoursesWithIds(courseIds);
            Group group = new Group();
            group.setGroupName(groupRequest.groupName());
            group.setDescription(groupRequest.description());
            group.setImageLink(groupRequest.imageLink());
            group.getCourses().addAll(courses);

            for (Course course : courses) {
                course.getGroups().add(group);
                group.getCourses().add(course);
            }
            groupRepository.save(group);
        }catch (Exception e){
            return  HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
        return  HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully created Groups !")
                .build();
    }

    @Override
    public List <GroupResponse> findAll() {
        return groupRepository.findAllGroups();
    }

    @Override
    public HTTPResponse deleteById(Long groupId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("Not found group "));
            groupRepository.delete(group);
            return HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message("Successfully deleted Groups !")
                    .build();
        }catch (NoSuchElementException e){
            return  HTTPResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override @Transactional
    public HTTPResponse update(Long groupId, GroupRequest groupRequest) {
        Group group = groupRepository.findById(groupId).orElseThrow();
        group.setGroupName(groupRequest.groupName());
        group.setDescription(groupRequest.description());
        group.setImageLink(groupRequest.imageLink());

        return HTTPResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated Groups !")
                .build();
    }

    @Override
    public List<String> countById(Long groupId) {
        return groupRepository.countById(groupId);
    }

}
