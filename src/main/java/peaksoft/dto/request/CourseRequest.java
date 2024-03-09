package peaksoft.dto.request;

import java.time.LocalDate;

public record CourseRequest(
        String courseName,
        String description,
        LocalDate dateOfStart) {

}

