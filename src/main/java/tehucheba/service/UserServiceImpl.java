package tehucheba.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tehucheba.AuthorizedUser;
import tehucheba.model.User;
import tehucheba.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements tehucheba.service.UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = repository.getByUserName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User " + name + " is not found");
        }
        AuthorizedUser authorizedUser = new AuthorizedUser(user);
        authorizedUser.getAuthorities();
        return authorizedUser;
    }
}
