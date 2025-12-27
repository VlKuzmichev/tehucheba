package tehucheba.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "questions", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "questions_unique_name_idx")})
public class Question extends AbstractNamedEntity {

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private List<Answer> answers;

    public Question(List<Answer> answers) {
        this.answers = answers;
    }

    public Question() {
    }

    public Question(Integer id, String name, List<Answer> answers) {
        super(id, name);
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                '}';
    }
}
