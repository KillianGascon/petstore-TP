package fr.epsi.b32526.entity;

import fr.epsi.b32526.entity.enums.ProdType;
import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @Column(name = "label", nullable = false)
    private String label;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ProdType type;
    @Column(name = "price", nullable = false)
    private double price;
    @ManyToMany(mappedBy = "products")
    private java.util.List<PetStore> petStores = new java.util.ArrayList<>();

    public Product() {}

    public Product(String code, String label, ProdType type, double price) {
        this.code  = code;
        this.label = label;
        this.type  = type;
        this.price = price;
    }

    public Long getId() { return id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public ProdType getType() { return type; }
    public void setType(ProdType type) { this.type = type; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public java.util.List<PetStore> getPetStores() { return petStores; }

    @Override
    public String toString() {
        return "[" + code + "] " + label + " (" + type + ") – " + price + "€";
    }
}
