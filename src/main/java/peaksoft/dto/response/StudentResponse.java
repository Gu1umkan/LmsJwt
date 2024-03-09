package peaksoft.dto.response;

import peaksoft.enums.StudyFormat;

public record StudentResponse(Long id, String firstName, String lastName, String phoneNumber,
                              String email, StudyFormat studyFormat) {
}
