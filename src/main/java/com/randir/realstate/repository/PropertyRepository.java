package com.randir.realstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.randir.realstate.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
