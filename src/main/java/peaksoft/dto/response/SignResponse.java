package peaksoft.dto.response;

import lombok.Builder;
import peaksoft.enums.Role;

@Builder
public record SignResponse(
        String token,
        Long id,
        Role role,
        String email
){
}
