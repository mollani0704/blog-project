package com.project.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

}
