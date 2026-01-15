package tehucheba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tehucheba.model.Question;

import java.util.List;
import java.util.Optional;


public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Override
    Optional<Question> findById(Integer id);

    @Override
    List<Question> findAll();

    @Modifying
    @Query("DELETE FROM Question q WHERE q.id=:id")
    int delete(@Param("id") int id);

}
