package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CourseRequest;
import peaksoft.dto.response.CourseResponse;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseAPI {
    private final CourseService courseService;

    @Secured({"ADMIN"})
    @GetMapping("/all/{companyId}")
    public List<CourseResponse> findAll(@PathVariable Long companyId, @RequestParam String ascOrDesc){
        return   courseService.findAllCourse(companyId,ascOrDesc);
    }

    @Secured({"ADMIN"})
    @PostMapping("/save/{companyId}")
    public HTTPResponse save(@PathVariable Long companyId, @RequestBody CourseRequest courseRequest){
        return courseService.save(companyId,courseRequest);
    }

    @Secured({"ADMIN"})
    @PutMapping("/update/{courseId}")
    public HTTPResponse update( @PathVariable Long courseId, @RequestBody CourseRequest courseRequest){
        return courseService.updatedById(courseId,courseRequest);
    }

    @Secured({"ADMIN"})
    @DeleteMapping("/delete/{courseId}")
    public HTTPResponse delete(@PathVariable Long courseId){
        return courseService.deleteById(courseId);
    }

}
