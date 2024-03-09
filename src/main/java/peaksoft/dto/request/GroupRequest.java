package peaksoft.dto.request;

import java.util.List;

public record GroupRequest(String groupName, String imageLink, String description, List<Long> coursesIds) {
}