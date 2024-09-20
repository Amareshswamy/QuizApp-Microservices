package com.project.Quiz_Service.controller;



import com.project.Quiz_Service.entities.QuestionWrapper;
import com.project.Quiz_Service.entities.QuizDto;
import com.project.Quiz_Service.entities.Response;
import com.project.Quiz_Service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController
{
    @Autowired
    QuizService quizService;

    //@CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto)
    {
        return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
                //new ResponseEntity<>("Im here at quiz section", HttpStatus.CREATED);
    }

   // @CrossOrigin
    //@CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
    {
        return quizService.getQuizQuestions(id);
    }

    //@CrossOrigin(origins = "http://localhost:8765")
    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses)
    {
        System.out.println(id +" *************");
        System.out.println(responses);
       return quizService.calculateResult(id,responses);
    }
}
