package nl.androidappfactory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nl.androidappfactory.domain.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
