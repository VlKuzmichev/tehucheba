package tehucheba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tehucheba.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    Optional<User> findById(Integer id);

    User getByUserName(String userName);
}
