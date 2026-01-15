package tehucheba;

import tehucheba.model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    public AuthorizedUser(User user) {
        super(user.getUserName(), user.getPassword(), user.getRoles());
    }
}
