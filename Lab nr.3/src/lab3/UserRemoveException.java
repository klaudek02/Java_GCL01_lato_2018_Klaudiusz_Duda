package lab3;

public class UserRemoveException extends ChatException{
    long id;
    UserRemoveException(long id)
    {
       this.id = id;
    };
    public String toString()
    {
        return "Wyjatek: Nie udalo sie wylogowac uzytkownika " + this.id;
    }
}
