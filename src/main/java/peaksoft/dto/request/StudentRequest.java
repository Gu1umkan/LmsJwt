package peaksoft.dto.request;

import peaksoft.enums.StudyFormat;

public record StudentRequest(String email,String password,String firstName, String lastName, String phoneNumber,  StudyFormat studyFormat) {
}
