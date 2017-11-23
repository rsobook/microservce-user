package si.fri.rsobook.metrics;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserMetrics {

    //TODO metrics injection
    /*@Inject
    @Metric(name = "users_returned")
    private Counter usersReturned;*/

    public void addUsersReturned(int count){
        //usersReturned.inc(count);
    }

    public boolean isHealthy(){
        return true;
    }

}
