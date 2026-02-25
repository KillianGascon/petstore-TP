package fr.epsi.b32526.repository;

import fr.epsi.b32526.entity.Address;
import jakarta.persistence.EntityManager;
import java.util.List;

public class AddressRepository {

    private final EntityManager em;

    public AddressRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Address address) {
        em.persist(address);
    }

    public Address findById(Long id) {
        return em.find(Address.class, id);
    }

    public List<Address> findAll() {
        return em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }
}
