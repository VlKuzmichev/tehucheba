package tehucheba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tehucheba.model.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Override
    Optional<Answer> findById(Integer id);

    @Override
    List<Answer> findAll();

    @Modifying
    @Query("DELETE FROM Answer a WHERE a.id=:id")
    int delete(@Param("id") int id);
}
