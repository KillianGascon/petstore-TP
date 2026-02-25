package fr.epsi.b32526.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "petstore")
public class PetStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "manager_name")
    private String managerName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "petstore_product",
        joinColumns        = @JoinColumn(name = "petstore_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Animal> animals = new ArrayList<>();

    public PetStore() {}

    public PetStore(String name, String managerName, Address address) {
        this.name        = name;
        this.managerName = managerName;
        this.address     = address;
    }

    public void addProduct(Product product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
            product.getPetStores().add(this);
        }
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getPetStores().remove(this);
    }

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
        animal.setPetStore(this);
    }

    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
        animal.setPetStore(null);
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    public List<Product> getProducts() { return products; }

    public List<Animal> getAnimals() { return animals; }

    @Override
    public String toString() {
        return "PetStore{id=" + id + ", name='" + name + "', manager='" + managerName + "'}";
    }
}
