package si.fri.rsobook.rest;

import com.kumuluz.ee.logs.cdi.Log;
import si.fri.rsobook.config.UserApiConfigProperties;
import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.exceptions.BusinessLogicTransactionException;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.User;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.metrics.UserMetrics;
import si.fri.rsobook.service.DatabaseService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

@RequestScoped
@Path("User")
public class UserResource extends CrudResource<UUID, User> {


    @Inject
    private UserMetrics userMetrics;

    @Inject
    private UserApiConfigProperties userApiConfigProperties;

    @Inject
    private DatabaseService databaseService;

    public UserResource() {
        super(User.class);
    }

    @PostConstruct
    private void postInit() {
        if(userApiConfigProperties.getListsUsers() != null){
            defaultMaxLimit = userApiConfigProperties.getListsUsers();
        }
    }

    @Override
    protected AuthEntity getAuthorizedEntity() {
        return null;
    }

    @Override
    protected DatabaseImpl getDatabaseService() {
        return databaseService;
    }

    @Override
    protected UUID parseId(String s) {
        return UUID.fromString(s);
    }


    @Override
    public Response getList() throws BusinessLogicTransactionException {
        Response res = super.getList();

        List<User> userList = (List<User>) res.getEntity();
        userMetrics.addUsersReturned(userList.size());

        return res;
    }

    @Log
    @Override
    public Response get(String rawId) throws BusinessLogicTransactionException {
        Response res = super.get(rawId);

        userMetrics.addUsersReturned(1);

        return res;
    }
}
