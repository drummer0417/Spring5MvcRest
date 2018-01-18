package nl.androidappfactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.androidappfactory.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Customer findByFirstName(String firstName);

	public Customer findByLastName(String lastName);
}
