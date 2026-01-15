package tehucheba.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class TestAnswer extends AbstractNamedEntity {

    @ManyToOne
    private Question question;
    @ManyToOne
    private Answer answer;

    public TestAnswer() {
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

}
