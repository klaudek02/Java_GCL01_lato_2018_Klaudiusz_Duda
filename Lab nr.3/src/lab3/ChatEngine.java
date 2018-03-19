package lab3;
import java.util.*;

public class ChatEngine {
    Map<Long, User> users = new HashMap<>();
    Map<String, User> users2 = new HashMap<>();
    void login(User user) throws UserLoginException
    {
        if(users.containsValue(user))
        {
            throw new UserLoginException(user);
        }
        user.id = users.size();
        users.put(user.id,user);
        users2.put(user.name, user);
        
        
    }
    void logout(long userId) throws UserRemoveException
    {
        if(users.remove(userId) == null)
        {
            throw new UserRemoveException(userId);
        }
        List<User> user=new ArrayList<>();
        User fromlist = null;
        for (User fromList: user) {
            if(fromList.id==userId)
            {
                users2.remove(fromList.name);
            }
        }
        
    }
    int getNumberOfUsers()
    {
        return users.size();
    }
    Object getKeyFromValue(Map hm, Object value)
    {
        for(Object o : hm.keySet())
        {
            if(hm.get(o).equals(value))
            {
                return o;
            }
        }
        return null;
    }
    
    void addUserStar(String userName)
    {
        List<User> user=new ArrayList<>();
        User fromlist = null;
        for (User fromList: user) {
            if(fromList.name==userName)
            {
                break;
            }
        }
        if(users.containsValue(userName))
        {
            Object key = getKeyFromValue(users, fromlist);
            User nowy = users.get(key);
            nowy.numberOfStars++;
            users.put((Long)key, nowy );
            
        }
    }
    void removeUserStar(String userName)
    {
        List<User> user=new ArrayList<>();
        User fromlist = null;
        for (User fromList: user) {
            if(fromList.name==userName)
            {
                break;
            }
        }
        Object key = getKeyFromValue(users, fromlist);
        User nowy = users.get(key);
        nowy.numberOfStars--;
        users.put((Long)key, nowy );
            
    }
    
    boolean hasUser(long userId)
    {
       if(users.containsKey(userId) == true)
       {
           return true;
       }
       return false;
    }
    boolean hasUser(String userName)
    {
        if(users2.containsKey(userName) == true)
            return true;
        return false;
        
    }

    

}
