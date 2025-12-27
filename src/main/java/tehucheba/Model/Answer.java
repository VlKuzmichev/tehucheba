package tehucheba.Model;

import javax.persistence.*;

@Entity
@Table(name = "answers", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "question_id"}, name = "answers_unique_name_question_id_idx")})
public class Answer extends AbstractNamedEntity {

    @Column(name = "right_answer", nullable = false)
    private Boolean rightAnswer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    public Answer() {
    }

    public Answer(Boolean rightAnswer, Question question) {
        this.rightAnswer = rightAnswer;
        this.question = question;
    }

    public Answer(String name, Boolean rightAnswer, Question question) {
        this.rightAnswer = rightAnswer;
        this.question = question;
    }

    public Answer(Integer id, String name, Boolean rightAnswer, Question question) {
        super(id, name);
        this.rightAnswer = rightAnswer;
        this.question = question;
    }

    public Boolean getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(Boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
