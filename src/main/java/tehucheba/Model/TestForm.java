package tehucheba.model;

import java.util.ArrayList;
import java.util.List;

public class TestForm {

    private String studentName;

    private List<TestAnswer> testAnswers = new ArrayList<>();

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public List<TestAnswer> getTestAnswers() {
        return testAnswers;
    }

    public void setTestAnswers(List<TestAnswer> testAnswers) {
        this.testAnswers = testAnswers;
    }

}
