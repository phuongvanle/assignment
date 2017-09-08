package com.dxc.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.assignment.model.TestExecution;
import com.dxc.assignment.model.SuitProject;

public interface SuitProjectRepository extends MongoRepository<SuitProject, String> {
	
	SuitProject findByName(String name);

}
