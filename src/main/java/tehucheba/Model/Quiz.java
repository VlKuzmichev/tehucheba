package tehucheba.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "quizzes", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "quizzes_unique_name_idx")})
public class Quiz extends AbstractNamedEntity {

    @Column(name = "min_score", nullable = false)
    private int minScore;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quiz_questions", joinColumns = {@JoinColumn(name = "quiz_id")},
            inverseJoinColumns = {@JoinColumn(name = "question_id")})
    private List<Question> questions;

    public Quiz() {
    }

    public Quiz(int maxScore) {
        this.minScore = maxScore;
    }

    public Quiz(Integer id, String name, int maxScore) {
        super(id, name);
        this.minScore = maxScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int maxScore) {
        this.minScore = maxScore;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minScore=" + minScore +
                '}';
    }
}
