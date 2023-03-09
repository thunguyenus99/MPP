package entity;

import java.io.Serializable;
import java.util.List;

final public class User implements Serializable {

    private static final long serialVersionUID = 5147265048973262104L;

    private String userId;

    private String password;

    private List<Role> authorizations;

    public User(String userId, String password, List<Role> authorizations) {
        this.userId = userId;
        this.password = password;
        this.authorizations = authorizations;
    }

    @Override
    public String toString() {
        return userId + ", " + password + ", " + authorizations.size();
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getAuthorizations() {
        return authorizations;
    }
}