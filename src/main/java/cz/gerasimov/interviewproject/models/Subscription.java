package cz.gerasimov.interviewproject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "subscription")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	@ManyToOne
	@JoinColumn(name = "quotation_id")
	Quotation quotation;
	ZonedDateTime startDate;
	ZonedDateTime validUntil;

	@Override
	public String toString() {
		return "Subscription{" +
				"id=" + id +
				", quotation=" + quotation +
				", startDate=" + startDate +
				", validUntil=" + validUntil +
				'}';
	}

}
