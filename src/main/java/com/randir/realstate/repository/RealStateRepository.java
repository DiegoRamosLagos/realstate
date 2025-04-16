package com.randir.realstate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.randir.realstate.entity.RealState;

public interface RealStateRepository extends JpaRepository<RealState, Integer> {

}
