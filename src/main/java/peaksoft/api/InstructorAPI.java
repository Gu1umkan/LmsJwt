package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.InstructorRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.InfoInstructorsResponse;
import peaksoft.dto.response.InstructorResponse;
import peaksoft.service.InstructorService;

import java.util.List;

@RestController
@RequestMapping("/api/instructor")
@RequiredArgsConstructor
public class InstructorAPI {
    private final InstructorService instructorService;

    @Secured({"ADMIN"})
    @GetMapping("/all/{companyId}")
    public List<InstructorResponse> findAll(@PathVariable Long companyId){
        return instructorService.findAll(companyId);
    }
    @Secured({"ADMIN"})
    @PostMapping("/save")
    public HTTPResponse save(@RequestBody InstructorRequest instructorRequest){
        return instructorService.save(instructorRequest);
    }
    @Secured({"ADMIN"})
    @PostMapping("assignCompany/{companyId}/{instructorId}")
    public HTTPResponse asSignInstructorToCompany(@PathVariable Long companyId, @PathVariable Long instructorId){
        return instructorService.assign(companyId,instructorId);
    }

    @Secured({"ADMIN"})
    @PostMapping("assignCourse/{courseId}/{instructorId}")
    public HTTPResponse asSignInstructorToCourse(@PathVariable Long courseId,@PathVariable Long instructorId){
        return instructorService.asSignCourse(courseId,instructorId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/update/{instructorId}")
    public HTTPResponse update(@PathVariable Long instructorId,@RequestBody InstructorRequest instructorRequest){
        return instructorService.update(instructorId,instructorRequest);
    }

    @Secured({"ADMIN"})
    @PostMapping("/delete/{instructorId}")
    public HTTPResponse delete(@PathVariable Long instructorId){
        return instructorService.delete(instructorId);
    }

    @Secured({"ADMIN"})
    @GetMapping("/instructorCount/{inId}")
    public Integer count(@PathVariable Long inId){
        return instructorService.countStudent(inId);
    }

    @Secured({"ADMIN"})
    @GetMapping("/info/{instructorId}")
    public InfoInstructorsResponse infos(@PathVariable Long instructorId){
        return instructorService.infoInstructor(instructorId);
    }

}

