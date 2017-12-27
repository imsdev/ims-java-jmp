package customer.info;

import policy.info.AutoPolicy;
import policy.info.HousePolicy;

public class CustomerService {

	public Customer getCustomerInfo(int customerNumber) {

		Customer customer = new Customer();
		customer.setFirstName("Paul");
		customer.setLastName("Smith");
		customer.setStreet("1000 Aster Drive");
		customer.setCity("Columbus");
		customer.setState("CA");
		customer.setZipCode("96611");
		customer.setDateOfBirth("01/01/1999");

		AutoPolicy autoPolicy = new AutoPolicy();
		autoPolicy.setPolicyNumber(1234);
		autoPolicy.setDriverName("Paul Smith");
		autoPolicy.setMake("Corrola");
		autoPolicy.setManufactureDate("01/01/1999");
		autoPolicy.setModel("Toyota");
		autoPolicy.setRegistrationNumber("M842PSE");
		
		HousePolicy housePolicy = new HousePolicy();
		housePolicy.setNumberOfBedrooms((short) 4);
		housePolicy.setPolicyNumber(234);
		housePolicy.setPropertyType("FLAT");
		housePolicy.setStreet("1000 Aster Drive");
		housePolicy.setValue(1000);
		housePolicy.setZipCode("96611");
		
		customer.getAutoPolicies().add(autoPolicy);
		customer.getHousePolicies().add(housePolicy);
		
		return customer;
	}
}
