package si.fri.rsobook.rest;

import si.fri.rsobook.core.database.dto.AuthEntity;
import si.fri.rsobook.core.database.impl.DatabaseImpl;
import si.fri.rsobook.core.model.User;
import si.fri.rsobook.core.restComponenets.resource.CrudResource;
import si.fri.rsobook.service.DatabaseService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import java.util.UUID;

@RequestScoped
@Path("User")
public class UserResource extends CrudResource<UUID, User> {

    @Inject
    private DatabaseService databaseService;

    public UserResource() {
        super(User.class);
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

}
