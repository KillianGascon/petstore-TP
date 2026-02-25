package fr.epsi.b32526.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth")
    private Date birth;

    @Column(name = "couleur")
    private String couleur;

    @ManyToOne
    @JoinColumn(name = "pet_store_id")
    private PetStore petStore;

    public Animal() {}

    public Animal(Date birth, String couleur) {
        this.birth   = birth;
        this.couleur = couleur;
    }

    public Long getId() { return id; }

    public Date getBirth() { return birth; }
    public void setBirth(Date birth) { this.birth = birth; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public PetStore getPetStore() { return petStore; }
    public void setPetStore(PetStore petStore) { this.petStore = petStore; }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (couleur=" + couleur + ", naissance=" + birth + ")";
    }
}
