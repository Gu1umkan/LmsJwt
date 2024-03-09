package peaksoft.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class CompanyInfoResponse {
    private Long id;
    private String name;
    private String country;
    private String address;
    private String phoneNumber;

   private List<String> courseName = new ArrayList<>();
   private List<String> groupName = new ArrayList<>();
   private List<String> instructorName = new ArrayList<>();

    private int countStudents ;

    public CompanyInfoResponse() {
    }

    public CompanyInfoResponse(Long id, String name, String country, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public void addCourseName(String name){
        if (this.courseName == null) this.courseName = new ArrayList<>();
        this.courseName.add(name);
    }
    public void addGroupName(String name){
        if (this.groupName == null) this.groupName = new ArrayList<>();
        this.groupName.add(name);
    }
    public void addInstructorName(String name){
        if (this.instructorName == null) this.instructorName = new ArrayList<>();
        this.instructorName.add(name);
    }
}
