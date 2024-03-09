package peaksoft.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.Specialization;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class InfoInstructorsResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Specialization specialization;
    public Map<String,Integer> groupsNameWithStudent = new HashMap<>() ;

    public InfoInstructorsResponse(Long id, String firstName, String lastName, String phoneNumber, Specialization specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
    }
}
