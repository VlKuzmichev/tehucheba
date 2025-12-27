package tehucheba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tehucheba.Model.Quiz;

import java.util.List;
import java.util.Optional;


public interface QuizRepository extends JpaRepository<Quiz, Integer> {

    @Override
    Optional<Quiz> findById(Integer id);

    @Override
    List<Quiz> findAll();

}
