package peaksoft.service;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyInfoResponse;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.exceptions.MyException;

import java.util.List;

public interface CompanyService {
    CompanyResponse save(CompanyRequest companyRequest) throws  MyException;
    CompanyRequest findById(Long companyId);


    List<CompanyRequest> findAll();

    CompanyResponse updateById(Long companyId, CompanyRequest companyRequest);

    CompanyResponse deleteById(Long companyId);

    CompanyInfoResponse findInfos(Long companyId);
}
