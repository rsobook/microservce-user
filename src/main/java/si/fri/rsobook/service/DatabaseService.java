package si.fri.rsobook.service;

import si.fri.rsobook.core.database.Database;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


@RequestScoped
@Transactional(Transactional.TxType.REQUIRED)
public class DatabaseService extends Database {

    @PersistenceContext(unitName = "user-jpa")
    private EntityManager em;

    @PostConstruct
    private void init(){
        this.entityManager = em;
    }

}
