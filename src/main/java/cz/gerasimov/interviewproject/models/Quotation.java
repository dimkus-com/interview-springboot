package cz.gerasimov.interviewproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "quotation")
public class Quotation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	ZonedDateTime beginingOfInsurance;
	BigDecimal insuredAmount;
	ZonedDateTime dateOfSigningMortgage;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	Customer customer;
	@OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Subscription> subscriptions;

	@Override
	public String toString() {
		return "Quotation{" +
				"id=" + id +
				", beginingOfInsurance=" + beginingOfInsurance +
				", insuredAmount=" + insuredAmount +
				", dateOfSigningMortgage=" + dateOfSigningMortgage +
				", customer=" + customer +
				'}';
	}

}
