package si.fri.rsobook.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("properties")
public class UserApiConfigProperties {

    @ConfigValue(watch = true)
    private Integer listsUsers;

    public Integer getListsUsers() {
        return listsUsers;
    }

    public void setListsUsers(Integer listsUsers) {
        this.listsUsers = listsUsers;
    }

}
