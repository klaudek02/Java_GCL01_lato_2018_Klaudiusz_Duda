package lab3;

public class UserLoginException extends ChatException{

    String nazwa;
    UserLoginException(User user)
    {
        nazwa = user.name;
    };
    public String toString()
    {
        return "Wyjatek: Podany uzytkownik istnieje" + nazwa;
    }

}
