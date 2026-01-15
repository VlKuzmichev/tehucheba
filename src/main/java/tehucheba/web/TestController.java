package tehucheba.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tehucheba.model.*;
import tehucheba.repository.AnswerRepository;
import tehucheba.repository.ProtocolRepository;
import tehucheba.repository.QuestionRepository;
import tehucheba.repository.QuizRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = "/tests")
public class TestController {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ProtocolRepository protocolRepository;

    public TestController(QuizRepository quizRepository, QuestionRepository questionRepository,
                          AnswerRepository answerRepository, ProtocolRepository protocolRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.protocolRepository = protocolRepository;
    }

    @GetMapping(value = "/{id}")
    public String questions(@PathVariable int id, Model model) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        List<Question> questions = quiz.getQuestions();
        Collections.shuffle(questions);
        // Prepare answers for test
        List<TestAnswer> testAnswers = new ArrayList<>();
        questions.forEach(question -> {
            question.getAnswers().forEach(answer -> answer.setRightAnswer(false));
            TestAnswer newTestAnswer = new TestAnswer();
            newTestAnswer.setQuestion(question);
            testAnswers.add(newTestAnswer);
        });

        TestForm testForm = new TestForm();
        testForm.setTestAnswers(testAnswers);
        model.addAttribute("testForm", testForm);
        return "tests";
    }

    @PostMapping(value = "/{id}")
    public String processForm(@PathVariable int id, @ModelAttribute("testForm") TestForm testForm, Model model) {
        Quiz quiz = quizRepository.findById(id).orElse(null);
        Protocol protocol = new Protocol();
        protocol.setName(testForm.getStudentName().trim());
        protocol.setTestDate(LocalDateTime.now());
        List<String> details = new ArrayList<>();
        int countAnswers = 0;
        for (TestAnswer a : testForm.getTestAnswers()) {
            String studentAnswer = "Неверно";
            try {
                studentAnswer = answerRepository.getById(a.getAnswer().getId()).getRightAnswer() ? "Верно" : "Неверно";
                if (studentAnswer == "Верно") {
                    countAnswers += 1;
                }
            } catch (Exception ignored) {
            }
            details.add(questionRepository.getById(a.getQuestion().getId()).getName() + " : " + studentAnswer);
        }
        protocol.setQuizName(quiz.getName());
        int countQuestions = quiz.getQuestions().size();
        protocol.setPercent(100 * countAnswers / countQuestions);
        protocol.setStatus(protocol.getPercent() >= quiz.getMinScore());
        StringBuilder protocolAnswers = new StringBuilder();
        for (String s : details) {
            protocolAnswers.append(s).append(";");
        }
        protocol.setDetails(protocolAnswers.toString());
        protocol.setMinScore(quiz.getMinScore());
        model.addAttribute("protocol", protocol);
        model.addAttribute("testDetails", details);
        Protocol foundProtocol = protocolRepository.getByQuizNameAndDate(protocol.getName(), quiz.getName(), LocalDate.now());
        if (foundProtocol == null) {
            protocolRepository.save(protocol);
            return "result";
        }
        return "denied";
    }
}
