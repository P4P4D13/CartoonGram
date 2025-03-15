package pk1;
/*========================================================
en:implement observer
ive created my own obsever since the java.util.obsever is depricated since java9
observer was depricated because it was a class not an interface, that means 
it limits the current class that wants to implement the observer to inherit the observer class 
thus leading to many limitation because as we know in java clases can only inherit one class
ro:am creat proprriul observer intrucat java.util.observer a fost depricat
in versiunea java9 fiind limitat ca functionalitate, observerul era o clasa si acest lucru
limita clasele sa mosteneasca numai observerul daca aveau nevoie de acesta
am implementat in metoda mea observerul cu ajutorul interfetei
*/
public class Follower implements FollowObserver {
    private String name;
    private FollowersWindow followersWindow;
    public Follower(String name, FollowersWindow followersWindow) {
        this.name = name;
        this.followersWindow = followersWindow;
    }

    @Override
    public void update(FollowSubject subject) 
    {
    	/*
    	en:the generic update method that updates the users with the details
    	in this case the user is updated with whom he/she follows
    	ro:clasa generica folosite in observer design patern in care
    	updateaza observatorii referitor la schimbarile subiectului
    	in cazul de fata utilizatorul este notificat doar pe cine a inceput sa urmareasca/sa nu urmareasca
    	*/
    	
        if (subject.isFollowed()) {
            String message = "Started following " + name;
            //this is the message that is sent to the console/acest mesaj e trimis in consola
            System.out.println(message);
            //a list of names is added to following/profilele sunt adaugate la hashset de follow
            followersWindow.addFollowing(name);
        } else {
        	//the same with unfollowing a person/la fel in caz de unfollow
            String message = "Unfollowed " + name;
            System.out.println(message);
            followersWindow.removeFollowing(name);
        }
    }
    
//name is private so we obviously need a getName
    //numele e privat deci avem nevoie de getname
    public String getName() {
        return name;
    }
}
