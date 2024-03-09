package peaksoft.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.HTTPResponse;
import peaksoft.service.GroupService;

import java.util.List;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupAPI {

    private final GroupService groupService;

    @Secured({"ADMIN"})
    @PostMapping("/save")
    public HTTPResponse save(@RequestBody GroupRequest groupRequest){
        return groupService.save(groupRequest);
    }

    @Secured({"ADMIN"})
    @GetMapping("/all")
    public List<GroupResponse> findAll(){
        return groupService.findAll();
    }

    @Secured({"ADMIN"})
    @PostMapping("/delete/{groupId}")
    public HTTPResponse delete(@PathVariable Long groupId){
        return groupService.deleteById(groupId);
    }

    @Secured({"ADMIN"})
    @PostMapping("/update/{groupId}")
    public HTTPResponse update(@PathVariable Long groupId,@RequestBody GroupRequest groupRequest){
        return groupService.update(groupId,groupRequest);
    }

//    @GetMapping("count/{groupId}")
//    public List<String> count(@PathVariable Long groupId){
//        return groupService.countById(groupId);
//    }

}

