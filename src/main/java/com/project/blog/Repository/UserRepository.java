package com.project.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
