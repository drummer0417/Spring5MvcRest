package nl.androidappfactory.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import nl.androidappfactory.domain.Category;
import nl.androidappfactory.domain.Customer;
import nl.androidappfactory.domain.Vendor;
import nl.androidappfactory.repositories.CategoryRepository;
import nl.androidappfactory.repositories.CustomerRepository;
import nl.androidappfactory.repositories.VendorRepository;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

	private CategoryRepository categoryRepository;
	private CustomerRepository customerRepository;
	private VendorRepository vendorRepository;

	public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		this.categoryRepository = categoryRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		loadCategoryData();

		loadCustomerData();

		loadVendorData();
	}

	private void loadCustomerData() {
		customerRepository.save(new Customer(null, "Hans", "van Meurs"));
		customerRepository.save(new Customer(null, "Jacky", "van Meurs"));
		customerRepository.save(new Customer(null, "Cas", "van Meurs"));
		customerRepository.save(new Customer(null, "Anour", "van Meurs"));
		customerRepository.save(new Customer(null, "Wilber", "van Leusden"));
		customerRepository.save(new Customer(null, "Henk", "P"));

		log.info("Customer data loaaded, #customers: " + customerRepository.count());
	}

	private void loadVendorData() {
		vendorRepository.save(new Vendor(null, "Western Tasty Fruits Ltd."));
		vendorRepository.save(new Vendor(null, "Exotic Fruits Company"));
		vendorRepository.save(new Vendor(null, "Home Fruits"));
		vendorRepository.save(new Vendor(null, "Fun Fresh Fruits Ltd."));
		vendorRepository.save(new Vendor(null, "Nuts for Nuts Company"));
		vendorRepository.save(new Vendor(null, "Android App Factory"));

		log.info("Vendor data loaaded, #vendors: " + vendorRepository.count());
	}

	private void loadCategoryData() {
		Category fruits = new Category();
		fruits.setName("fruits");

		Category dried = new Category();
		dried.setName("dried");

		Category fresh = new Category();
		fresh.setName("fresh");

		Category exotic = new Category();
		exotic.setName("exotic");

		Category nuts = new Category();
		nuts.setName("nuts");

		categoryRepository.save(fruits);
		categoryRepository.save(dried);
		categoryRepository.save(fresh);
		categoryRepository.save(exotic);
		categoryRepository.save(nuts);

		log.info("Category data loaaded, #categories: " + categoryRepository.count());
	}

	public String test(String s) {

		return "Test" + s;
	}
}
