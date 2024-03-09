package peaksoft.dto.request;

import peaksoft.enums.Specialization;

public record InstructorRequest(String email, String password,
                                String firstName, String lastName, String phoneNumber,
                                Specialization specialization) {
}
