package peaksoft.dto.response;

import peaksoft.enums.Specialization;

public record InstructorResponse(Long id, String firstName, String lastName, String phoneNumber, Specialization specialization) {
}

