package com.project.Quiz_Service.service;


import com.project.Quiz_Service.dao.QuizDao;
import com.project.Quiz_Service.entities.QuestionWrapper;
import com.project.Quiz_Service.entities.Quiz;
import com.project.Quiz_Service.entities.Response;
import com.project.Quiz_Service.feing.QuizInterface;
import org.hibernate.query.spi.QueryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService
{

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

//    @Autowired
//    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title)
    {
        System.out.println(category + " " + numQ + " " + title);
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category,numQ).getBody();
        System.out.println("***************" + questions);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id)
    {

       Quiz quiz = quizDao.findById(id).get();
       List<Integer> questionsIds = quiz.getQuestionIds();
      // quizInterface.getQuestionsFromId(questionsIds);

        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionsIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {

        System.out.println("********** "+ id);
        System.out.println(responses);
       ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
