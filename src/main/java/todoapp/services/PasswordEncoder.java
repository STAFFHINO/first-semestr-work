package todoapp.services;

public interface PasswordEncoder {
    boolean matches(String password, String hash_password);
    String encode(String password);
}
