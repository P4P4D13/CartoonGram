package pk1;

import java.util.ArrayList;
import java.util.List;

public class FollowSubject {
    private List<FollowObserver> observers;//a list is created for all the observers that need to get notified
    //o lista de observatori este creata pentru toti cei care trebuie anuntati in caz de o schimbare de state a subjectului
    	
    private boolean isFollowed;//keeping track of the following state
//verifica state ul de follow
    public FollowSubject() //initialization of the observers/initializare observatori
    {
        observers = new ArrayList<>();
        isFollowed = false;
    }
//adding the observer in the list/adaugare observator in lista 
    public void attach(FollowObserver observer) {
        observers.add(observer);
    }
//excluding the observer from the list/excludere observator
    
    public void detach(FollowObserver observer) {
        observers.remove(observer);
    }

    //notify all the observer in case of change;
    public void notifyObservers() 
    {
        for (FollowObserver observer : observers) {
            observer.update(this);
        }
    }

    public boolean isFollowed() {
        return isFollowed;
    }

    public void setFollowed(boolean isFollowed, FollowersWindow followersWindow, String followerName) //setting the user to follow the profile
    {
        this.isFollowed = isFollowed;
        notifyObservers(); // ensure observers are notified first
        if (isFollowed) {
            followersWindow.addFollowing(followerName);
        } else {
            followersWindow.removeFollowing(followerName);
        }
    }

    public List<FollowObserver> getObservers() {
        return observers;
    }
}
