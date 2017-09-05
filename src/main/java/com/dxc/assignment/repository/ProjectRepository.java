package com.dxc.assignment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.dxc.assignment.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {
	
	Project findByName(String name);

}
