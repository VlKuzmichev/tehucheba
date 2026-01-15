package tehucheba.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tehucheba.model.EditQuizForm;
import tehucheba.model.Question;
import tehucheba.model.Quiz;
import tehucheba.repository.QuestionRepository;
import tehucheba.repository.QuizRepository;

import java.util.List;

@Controller
@RequestMapping(value = "/edit_quizzes")
public class QuizEditController {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public QuizEditController(QuizRepository quizRepository, QuestionRepository questionRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping()
    public String quizzes(Model model) {
        List<Quiz> quizzes = quizRepository.findAll();
        model.addAttribute("quizzes", quizzes);
        return "edit_quizzes";
    }

    @GetMapping(value = "/{id}")
    public String edit_quiz(@PathVariable int id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        EditQuizForm editQuizForm = new EditQuizForm();
        editQuizForm.setId(quiz.getId());
        editQuizForm.setName(quiz.getName());
        editQuizForm.setMinScore(quiz.getMinScore());
        editQuizForm.setQuestions(quiz.getQuestions());
        model.addAttribute("editQuizForm", editQuizForm);
        return "edit_quiz";
    }

    @GetMapping(value = "/add")
    public String add_quiz(Model model) {
        Quiz quiz = new Quiz();
        EditQuizForm addQuizForm = new EditQuizForm();
        addQuizForm.setName(quiz.getName());
        addQuizForm.setMinScore(quiz.getMinScore());
        model.addAttribute("addQuizForm", addQuizForm);
        return "add_quiz";
    }

    @GetMapping(value = "/remove/{id}")
    public String remove_quiz(@PathVariable int id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        quizRepository.delete(quiz);
        return "redirect:/edit_quizzes";
    }

    @PostMapping()
    public String addForm(@ModelAttribute("addQuizForm") EditQuizForm addQuizForm, Model model) {
        Quiz quiz = new Quiz();
        quiz.setMinScore(addQuizForm.getMinScore());
        quiz.setName(addQuizForm.getName());
        quizRepository.save(quiz);
        return "redirect:/edit_quizzes";
    }

    @PostMapping(value = "/{id}")
    public String editForm(@PathVariable int id, @ModelAttribute("editQuizForm") EditQuizForm editQuizForm, Model model) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        quiz.setMinScore(editQuizForm.getMinScore());
        quiz.setName(editQuizForm.getName());
        quiz.setQuestions(editQuizForm.getQuestions());
        quizRepository.save(quiz);
        return "edit_quizzes";
    }


}