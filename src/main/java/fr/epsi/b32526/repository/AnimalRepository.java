package fr.epsi.b32526.repository;

import fr.epsi.b32526.entity.Animal;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AnimalRepository {

    private final EntityManager em;

    public AnimalRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Animal animal) {
        em.persist(animal);
    }

    public Animal findById(Long id) {
        return em.find(Animal.class, id);
    }

    public List<Animal> findAll() {
        return em.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
    }

    public List<Animal> findByPetStore(Long petStoreId) {
        return em.createQuery(
                "SELECT a FROM Animal a WHERE a.petStore.id = :storeId", Animal.class)
                .setParameter("storeId", petStoreId)
                .getResultList();
    }
}
