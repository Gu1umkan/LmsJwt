package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StudentRequest;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.dto.response.StudentResponse;
import peaksoft.enums.StudyFormat;
import peaksoft.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentAPI {
    private final StudentService studentService;

    @Secured({"ADMIN"})
    @PostMapping("/save")
    public HTTPResponse save(@RequestBody StudentRequest studentRequest){
        return studentService.save(studentRequest);
    }

    @Secured({"ADMIN"})
    @PostMapping("assign/{groupId}/{studentId}")
    public HTTPResponse asSign(@PathVariable Long groupId, @PathVariable Long studentId){
        return studentService.asSign(groupId,studentId);
    }
    @Secured({"ADMIN"})
    @PostMapping ("delete/{studentId}")
    public HTTPResponse delete(@PathVariable Long studentId){
        return studentService.deleteById(studentId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/update/{studentId}")
    public HTTPResponse update(@PathVariable Long studentId,@RequestBody StudentRequest studentRequest){
        return studentService.update(studentId,studentRequest);
    }
    @Secured({"ADMIN"})
    @GetMapping("/all")
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }

    @Secured({"ADMIN"})
    @GetMapping ("/sortFormat")
    public List<StudentResponse> sortByFormat(@RequestParam StudyFormat studyFormat){
        return studentService.sortByFormat(studyFormat);
    }
    @Secured({"ADMIN"})
    @PostMapping("/block/{studentId}")
    public HTTPResponse blockStudent(@PathVariable Long studentId){
        return studentService.blockStudent(studentId);
    }
}
