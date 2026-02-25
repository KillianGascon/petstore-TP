package fr.epsi.b32526.repository;

import fr.epsi.b32526.entity.PetStore;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PetStoreRepository {

    private final EntityManager em;

    public PetStoreRepository(EntityManager em) {
        this.em = em;
    }

    public void save(PetStore petStore) {
        em.persist(petStore);
    }

    public PetStore findById(Long id) {
        return em.find(PetStore.class, id);
    }

    public List<PetStore> findAll() {
        return em.createQuery("SELECT ps FROM PetStore ps", PetStore.class).getResultList();
    }
}
