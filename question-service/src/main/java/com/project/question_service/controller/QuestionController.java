package com.project.question_service.controller;



import com.project.question_service.entities.Question;
import com.project.question_service.entities.QuestionWrapper;
import com.project.question_service.entities.Response;
import com.project.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @GetMapping("/allquestions")
    public List<Question> getAllQuestions()
    {
        return questionService.getAllQuestions();
    }


    @GetMapping("/category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable String category)
    {
        System.out.println("start of by category");
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public String addQuestion(@RequestBody Question question)
    {
        return questionService.addQuestions(question);
    }


    @PatchMapping("/update/{id}")
    public String updateQuestion(@PathVariable Integer id, @RequestBody Question question)
    {
        return questionService.updateQuestions(id,question);
    }


    @DeleteMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Integer id)
    {
        return questionService.deleteQuestion(id);
    }


    //generate
    //getQuestions (questionId)
    //getScore

    //@CrossOrigin
    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String  categoryName, @RequestParam Integer numQuestions)
    {
        return questionService.getQuestionsForQuiz(categoryName,numQuestions);
    }

   // @CrossOrigin
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

   // @CrossOrigin
    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }
}
