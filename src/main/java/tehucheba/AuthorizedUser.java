package tehucheba;

import tehucheba.Model.User;

public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    public AuthorizedUser(User user) {
        super(user.getUserName(), user.getPassword(), user.getRoles());
    }
}
