package fr.epsi.b32526.entity;

import fr.epsi.b32526.entity.enums.FishLivEnv;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fish")
@PrimaryKeyJoinColumn(name = "animal_id")
public class Fish extends Animal {

    @Enumerated(EnumType.STRING)
    @Column(name = "living_env", nullable = false)
    private FishLivEnv livingEnv;

    public Fish() {}

    public Fish(Date birth, String couleur, FishLivEnv livingEnv) {
        super(birth, couleur);
        this.livingEnv = livingEnv;
    }

    public FishLivEnv getLivingEnv() { return livingEnv; }
    public void setLivingEnv(FishLivEnv livingEnv) { this.livingEnv = livingEnv; }

    @Override
    public String toString() {
        return super.toString() + " [env=" + livingEnv + "]";
    }
}
