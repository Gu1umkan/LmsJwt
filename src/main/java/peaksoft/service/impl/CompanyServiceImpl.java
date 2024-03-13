package peaksoft.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyInfoResponse;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.entity.Company;
import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Instructor;
import peaksoft.exceptions.MyException;
import peaksoft.repo.CompanyRepo;
import peaksoft.service.CompanyService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepo companyRepository;

    @Override
    public CompanyResponse save(CompanyRequest companyRequest) throws MyException {
        boolean exist = companyRepository.existsByName(companyRequest.getName());
        if (exist) throw  new MyException("Company with name " + companyRequest.getName() +" already exist !");
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return CompanyResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully saved Company")
                .build();
    }

    @Override
    public CompanyRequest findById(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow();
        CompanyRequest companyRequest = new CompanyRequest();
        companyRequest.setName(company.getName());
        companyRequest.setAddress(company.getAddress());
        companyRequest.setCountry(company.getCountry());
        companyRequest.setPhoneNumber(company.getPhoneNumber());
        return companyRequest;
    }

    @Override
    public List<CompanyRequest> findAll() {
        List<Company> all = companyRepository.findAll();
        List<CompanyRequest> companyRequests = new ArrayList<>();
        for (Company company : all) {
            companyRequests.add(new CompanyRequest(company.getName(), company.getCountry(), company.getAddress(), company.getPhoneNumber()));
        }
        return companyRequests;
    }

    @Override @Transactional
    public CompanyResponse updateById(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Company with " + companyId + "not found"));
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        return CompanyResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully updated !")
                .build();
    }

    @Override
    public CompanyResponse deleteById(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id" + companyId + "not found"));
        companyRepository.delete(company);
        return CompanyResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Successfully deleted !")
                .build();
    }

    @Override
    public CompanyInfoResponse findInfos(Long companyId) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id" + companyId + "not found"));
        CompanyInfoResponse companyInfoResponse = companyRepository.fullCompanyInfo(companyId);
        for (Instructor instructor : company.getInstructors()) {
            companyInfoResponse.addInstructorName(instructor.getUser().getFirstName());
        }
        int count = 0;
        for (Course course : company.getCourses()) {
            companyInfoResponse.addCourseName(course.getCourseName());
            for (Group group : course.getGroups()) {
                companyInfoResponse.addGroupName(group.getGroupName());
                count += group.getStudents().size();
            }
        }
        companyInfoResponse.setCountStudents(count);
        return companyInfoResponse;

    }


}
