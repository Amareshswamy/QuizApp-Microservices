package com.project.Quiz_Service.dao;


import com.project.Quiz_Service.entities.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>
{

}
