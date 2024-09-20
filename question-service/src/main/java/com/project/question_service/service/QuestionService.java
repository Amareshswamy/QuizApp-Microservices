package com.project.question_service.service;


import com.project.question_service.dao.QuestionDao;
import com.project.question_service.entities.Question;
import com.project.question_service.entities.QuestionWrapper;
import com.project.question_service.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }


    public List<Question> getQuestionsByCategory(String category) {
        System.out.println("inside the service");
        return questionDao.findByCategoryIgnoreCase(category);
    }


    public String addQuestions(Question question) {
        questionDao.save(question);
        return "Success";
    }

    public String updateQuestions(Integer id, Question question) {
        Question question1 = questionDao.findById(id).orElseThrow(() -> new RuntimeException("No question found on this Id"));

        question1.setQuestionTitle(question.getQuestionTitle());
        question1.setOption1(question.getOption1());
        question1.setOption2(question.getOption2());
        question1.setOption3(question.getOption3());
        question1.setOption4(question.getOption4());
        question1.setCategory(question.getCategory());
        question1.setDifficultyLevel(question.getDifficultyLevel());
        question1.setRightAnswer(question.getRightAnswer());
        questionDao.save(question1);

        return "Updated the Changes";
    }

    public String deleteQuestion(Integer id) {
        questionDao.deleteById(id);
        return "Delete Successful";
    }


    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, Integer numQuestions) {
        List<Integer> questions = questionDao.findRandomQuestionsByCategory(categoryName, numQuestions);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
        List<QuestionWrapper> wrappers = new ArrayList<>();
        List<Question> questions = new ArrayList<>();
        for (Integer id : questionIds) {
            questions.add(questionDao.findById(id).get());
        }

        for (Question q : questions) {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(q.getId());
            wrapper.setQuestionTitle(q.getQuestionTitle());
            wrapper.setOption1(q.getOption1());
            wrapper.setOption2(q.getOption2());
            wrapper.setOption3(q.getOption3());
            wrapper.setOption4(q.getOption4());
            wrappers.add(wrapper);

        }
        return new ResponseEntity<>(wrappers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses)
    {
        int right=0;
        for(Response response : responses)
        {
            Question question = questionDao.findById(response.getId()).get();
            if(response.getResponse().equals(question.getRightAnswer())) {
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}