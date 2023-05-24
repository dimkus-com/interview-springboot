package cz.gerasimov.interviewproject.repositories;

import cz.gerasimov.interviewproject.models.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Integer> {
}
