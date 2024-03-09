package peaksoft.service;

import peaksoft.dto.request.GroupRequest;
import peaksoft.dto.response.GroupResponse;
import peaksoft.dto.response.HTTPResponse;

import java.util.List;

public interface GroupService {
    HTTPResponse save(GroupRequest groupRequest);

    List<GroupResponse> findAll();

    HTTPResponse deleteById(Long groupId);

    HTTPResponse update(Long groupId, GroupRequest groupRequest);

    List<String> countById(Long groupId);
}
