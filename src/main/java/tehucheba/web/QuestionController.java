package tehucheba.web;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tehucheba.Model.Answer;
import tehucheba.Model.EditQuestionForm;
import tehucheba.Model.Question;
import tehucheba.repository.AnswerRepository;
import tehucheba.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/edit_questions")
public class QuestionController {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionController(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }


    @GetMapping()
    public String questions(Model model) {
        List<Question> questions = questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        model.addAttribute("questions", questions);
        return "questions";
    }

    @GetMapping(value = "/{id}")
    public String viewQuestion(@PathVariable int id, Model model) {
        Question question = questionRepository.findById(id).orElse(null);
        EditQuestionForm questionForm = new EditQuestionForm();
        questionForm.setId(question.getId());
        questionForm.setName(question.getName());
        questionForm.setAnswers(question.getAnswers());
        model.addAttribute("questionForm", questionForm);
        return "edit_question";
    }

    @GetMapping(value = "/add")
    public String addQuestion(Model model) {
        EditQuestionForm questionForm = new EditQuestionForm();
        model.addAttribute("questionForm", questionForm);
        return "edit_question";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove_question(@PathVariable int id, Model model) {
        Question question = questionRepository.findById(id).orElse(null);
        assert question != null;
        questionRepository.delete(question);
        return "redirect:/edit_questions";
    }


    @Transactional
    @PutMapping()
    public String addForm(@RequestBody EditQuestionForm questionForm, Model model) {
        Question question = new Question();
        question.setName(questionForm.getName());
        List<Answer> formAnswers = questionForm.getAnswers();
        Question savedQuestion = questionRepository.save(question);
        List<Answer> addedAnswers = new ArrayList<>();
        formAnswers.forEach(answer -> {
            Answer newAnswer = new Answer();
            newAnswer.setName(answer.getName());
            newAnswer.setRightAnswer(answer.getRightAnswer());
            newAnswer.setQuestion(savedQuestion);
            addedAnswers.add(newAnswer);
        });
        answerRepository.saveAll(addedAnswers);
        return "questions";
    }

    @Transactional
    @PostMapping(value = "/{id}")
    public String editForm(@PathVariable int id, @RequestBody EditQuestionForm questionForm, Model model) {
        Question question = questionRepository.findById(id).orElse(null);
        question.setName(questionForm.getName());
        questionRepository.save(question);
        List<Answer> addedAnswers = new ArrayList<>();
        List<Answer> answers = question.getAnswers();
        List<Answer> formAnswers = questionForm.getAnswers();
        formAnswers.forEach(answer -> {
            if (answer.getId() == null) {
                Answer newAnswer = new Answer();
                newAnswer.setName(answer.getName());
                newAnswer.setRightAnswer(answer.getRightAnswer());
                newAnswer.setQuestion(question);
                addedAnswers.add(newAnswer);
            } else {
                addedAnswers.add(answer);
            }
        });
        answers.removeIf(addedAnswers::contains);
        answerRepository.saveAll(addedAnswers);
        answerRepository.deleteAll(answers);
        return "questions";
    }

}
