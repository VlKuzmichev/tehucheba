package tehucheba.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "protocols")
public class Protocol extends AbstractNamedEntity {

    @Column(name = "quiz_name", nullable = false)
    private String quizName;

    @Column(name = "test_date", nullable = false)
    private LocalDateTime testDate;

    @Column(name = "percent", nullable = false)
    private Integer percent;

    @Column(name = "status", nullable = false)
    private Boolean status;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "min_score", nullable = false)
    private int minScore;


    public Protocol() {

    }

    public Protocol(String quizName, LocalDateTime testDate, Integer percent, Boolean status, String details, int minScore) {
        this.quizName = quizName;
        this.testDate = testDate;
        this.percent = percent;
        this.status = status;
        this.details = details;
        this.minScore = minScore;
    }

    public Protocol(Integer id, String name, String quizName, LocalDateTime testDate, Integer percent, Boolean status, String details, int minScore) {
        super(id, name);
        this.quizName = quizName;
        this.testDate = testDate;
        this.percent = percent;
        this.status = status;
        this.details = details;
        this.minScore = minScore;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    @Override
    public String toString() {
        return "Protocol{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quizName='" + quizName + '\'' +
                ", testDate=" + testDate +
                ", percent=" + percent +
                ", status=" + status +
                ", details='" + details + '\'' +
                ", minScore=" + minScore +
                '}';
    }
}
