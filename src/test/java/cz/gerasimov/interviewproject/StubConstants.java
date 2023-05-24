package cz.gerasimov.interviewproject;

import java.math.BigDecimal;
import java.time.ZoneId;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public final class StubConstants {

	public static ZoneId ZONE_UTC = ZoneId.of("UTC");
	public static BigDecimal BIG_DECIMAL_VALUE = BigDecimal.valueOf(9999.99999);
	public static String BIG_DECIMAL_STRING = "9999.99999";

	public static final String CUSTOMER_FIRST_NAME = "Dmitriy";
	public static final String CUSTOMER_LAST_NAME = "Gerasimov";
	public static final String CUSTOMER_MIDDLE_NAME = "UNKNOWN";
	public static final String CUSTOMER_MIDDLE_NAME2 = "NONE";
	public static final String CUSTOMER_BIRTH_DAY = "2023-01-01";
	public static final String CUSTOMER_DAY = "2023-05-24";
	public static final String CUSTOMER_EMAIL = "1@1.1";
	public static final String CUSTOMER_EMAIL2 = "2@2.2";
	public static final int CUSTOMER_ID = 1111;
	public static final int CUSTOMER_ID2 = 2222;

	public static final int QUOTATION_ID = 222;
	public static final String QUOTATION_DATE_INSURANCE_GIVEN = "2023-04-24T15:01:14Z";
	public static final String QUOTATION_DATE_INSURANCE_EXPECTED = "2023-04-24T15:01:14Z[UTC]";
	public static final String QUOTATION_DATE_MORTGAGE_GIVEN = "2023-04-23T17:55:44+02:00[Europe/Prague]";
	public static final String QUOTATION_DATE_MORTGAGE_EXPECTED = "2023-04-23T15:55:44Z[UTC]";

	public static final int SUBSCRIPTION_ID = 33;
	public static final String SUBSCRIPTION_START_DATE_GIVEN = "2023-04-24T15:01:14Z";
	public static final String SUBSCRIPTION_START_DATE_EXPECTED = "2023-04-24T15:01:14Z[UTC]";
	public static final String SUBSCRIPTION_VALID_UNTIL_GIVEN = "2024-04-24T15:01:14Z[UTC]";
	public static final String SUBSCRIPTION_VALID_UNTIL_EXPECTED = "2024-04-24T15:01:14Z[UTC]";

	private StubConstants() {}


}
