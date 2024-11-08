package com.star.galeri_web_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.galeri_web_app.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
