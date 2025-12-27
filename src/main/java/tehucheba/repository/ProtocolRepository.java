package tehucheba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tehucheba.Model.Protocol;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ProtocolRepository extends JpaRepository<Protocol, Integer> {

    @Override
    Protocol save(Protocol protocol);

    @Override
    Optional<Protocol> findById(Integer id);

    @Query(nativeQuery = true, value = "select * from protocols WHERE name=:name ORDER BY test_date DESC LIMIT 1")
    Protocol findByName(String name);

    @Query(nativeQuery = true, value = "select * from protocols WHERE name=:studentName AND quiz_name=:quizName AND CAST(test_date AS date)=:date")
    Protocol getByQuizNameAndDate(String studentName, String quizName, LocalDate date);

    @Override
    @Query(nativeQuery = true, value = "select * from protocols ORDER BY test_date DESC LIMIT 1000")
    List<Protocol> findAll();
}
