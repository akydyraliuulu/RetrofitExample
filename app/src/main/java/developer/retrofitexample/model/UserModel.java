package developer.retrofitexample.model;

/**
 * Created by appaz on 10/19/17.
 */

public class UserModel {

    private String login;
    private String blog;
    private int public_repos;
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public String getBlog() {
        return blog;
    }

    public int getPublicRepos() {
        return public_repos;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
