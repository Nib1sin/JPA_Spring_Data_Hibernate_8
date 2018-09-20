package com.nibi.springdata.associations;




import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.nibi.springdata.associations.onetomany.entities.Customer;
import com.nibi.springdata.associations.onetomany.entities.PhoneNumber;
import com.nibi.springdata.associations.onetomany.repos.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssociationsApplicationTests {

	@Autowired
	CustomerRepository repository;
	
	@Test
	public void contextLoads() {
	}

	
	
	
	//
	@Test
	public void testCreateCustomer() {
		
		Customer customer = new Customer();
		customer.setName("Nibi");
//		HashSet<PhoneNumber> numbers = new HashSet<PhoneNumber>();
		
		PhoneNumber ph1 = new PhoneNumber();
		ph1.setNumber("123456789");
		ph1.setType("Cell");
//		ph1.setCustomer(customer);
//		numbers.add(ph1);
		
		PhoneNumber ph2 = new PhoneNumber();
		ph2.setNumber("987654321");
		ph2.setType("home");
//		ph2.setCustomer(customer);
//		numbers.add(ph2);
		
//		customer.setNumbers(numbers);
		
		customer.addPhoneNumber(ph1);
		customer.addPhoneNumber(ph2);
		
		repository.save(customer);
		
	}
	
	
	
	
	//
	@Test
	@Transactional
	public void testLoadCustomer() {
		
		Customer customer = repository.findById(4l).get();
		System.out.println(customer.getName());
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number -> System.out.println(number.getNumber()));
	}
	
	
	
	
	
	
	//
	@Test
//	@Transactional					// Para que funcione con fetchType.LAZY	
	@Rollback(false)				// Para que funcione el update es necesaio 
	public void testUpdateCustomer() {
		
		Customer customer = repository.findById(4l).get();
		customer.setName("Nibi Smirnov");
		
		Set<PhoneNumber> numbers = customer.getNumbers();
		numbers.forEach(number -> number.setType("cell"));
		
		repository.save(customer);
		
	}
	
	
	
	
	
	
	@Test
	public void testDelete() {
		
		repository.deleteById(4l);
		
	}
	
}
