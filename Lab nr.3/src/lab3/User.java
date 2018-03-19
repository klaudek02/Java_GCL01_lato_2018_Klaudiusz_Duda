package lab3;

public class User {
 
    long id;
    String name;
    long numberOfStars;
    long numberOfSentMessages;
    String getName()
    {
        return this.name;
    };
    long getNumberOfStars()
    {
        return numberOfStars;
    }
    long getNumberOfSentMessages()
    {
        return numberOfSentMessages;
    }
    User(String nazwa)
    {
        this.name = nazwa;
    }
    void setUser(long id, String name, long numberOfStars, long numberOfSentMessages)
    {
        this.id = id;
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.numberOfSentMessages = numberOfSentMessages;
    }
            
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        else
        {
            return true;
        }
        return false;
    }
}
