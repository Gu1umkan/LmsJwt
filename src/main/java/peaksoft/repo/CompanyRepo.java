package peaksoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CompanyInfoResponse;
import peaksoft.entity.Company;

@Repository
public interface CompanyRepo extends JpaRepository<Company,Long> {
    @Query("select case when count(c) > 0 then true else false end from Company c where c.name = :name")
    boolean existsByName(String name);

    @Query("""
select new peaksoft.dto.response.CompanyInfoResponse(c.id,c.name,c.country,c.address,c.phoneNumber) from Company c where c.id =:companyId
""")
    CompanyInfoResponse fullCompanyInfo(Long companyId);
}
