package fr.epsi.b32526;

import fr.epsi.b32526.entity.*;
import fr.epsi.b32526.entity.enums.FishLivEnv;
import fr.epsi.b32526.entity.enums.ProdType;
import fr.epsi.b32526.repository.*;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("petstore-pu");
        EntityManager em = emf.createEntityManager();

        try {
            if (new ProductRepository(em).findAll().isEmpty()) {
                seedData(em);
            }
            queryAnimals(em);
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void seedData(EntityManager em) {
        em.getTransaction().begin();

        ProductRepository   productRepo   = new ProductRepository(em);
        PetStoreRepository  petStoreRepo  = new PetStoreRepository(em);
        AnimalRepository    animalRepo    = new AnimalRepository(em);

        Product food      = new Product("FOOD-001",  "Croquettes premium",  ProdType.FOOD,      12.99);
        Product accessory = new Product("ACC-001",   "Griffoir deluxe",     ProdType.ACCESSORY, 24.50);
        Product cleaning  = new Product("CLN-001",   "Litière ultra-absorbante", ProdType.CLEANING, 8.75);

        productRepo.save(food);
        productRepo.save(accessory);
        productRepo.save(cleaning);

        PetStore store1 = new PetStore(
                "Animalia Paris",
                "Marie Dupont",
                new Address("12", "Rue de la Paix", "75001", "Paris"));

        PetStore store2 = new PetStore(
                "Zoo Plus Lyon",
                "Jean Martin",
                new Address("5", "Rue Victor Hugo", "69002", "Lyon"));

        PetStore store3 = new PetStore(
                "PetWorld Bordeaux",
                "Sophie Bernard",
                new Address("8", "Allée des Fleurs", "33000", "Bordeaux"));

        store1.addProduct(food);
        store1.addProduct(accessory);
        store2.addProduct(food);
        store2.addProduct(cleaning);
        store3.addProduct(accessory);
        store3.addProduct(cleaning);

        petStoreRepo.save(store1);
        petStoreRepo.save(store2);
        petStoreRepo.save(store3);

        Fish nemo     = new Fish(date(2023, 3, 10), "Orange et blanc", FishLivEnv.SEA_WATER);
        Fish goldie   = new Fish(date(2022, 7, 20), "Or",              FishLivEnv.FRESH_WATER);
        Fish dory     = new Fish(date(2023, 1, 5),  "Bleu et noir",    FishLivEnv.SEA_WATER);

        Cat whiskers  = new Cat(date(2021, 5, 15), "Gris tigré",  "CHIP-FR-001");
        Cat luna      = new Cat(date(2022, 11, 2), "Noir",         "CHIP-FR-002");
        Cat minou     = new Cat(date(2020, 8, 30), "Roux et blanc","CHIP-FR-003");

        store1.addAnimal(nemo);
        store1.addAnimal(goldie);
        store1.addAnimal(whiskers);
        store2.addAnimal(dory);
        store2.addAnimal(luna);
        store3.addAnimal(minou);

        animalRepo.save(nemo);
        animalRepo.save(goldie);
        animalRepo.save(dory);
        animalRepo.save(whiskers);
        animalRepo.save(luna);
        animalRepo.save(minou);

        em.getTransaction().commit();

        System.out.println("=== Données insérées avec succès ===");
        System.out.println("Produits : " + productRepo.findAll().size());
        System.out.println("PetStores : " + petStoreRepo.findAll().size());
        System.out.println("Animaux : " + animalRepo.findAll().size());
    }

    private static void queryAnimals(EntityManager em) {
        AnimalRepository animalRepo = new AnimalRepository(em);

        PetStoreRepository petStoreRepo = new PetStoreRepository(em);
        List<PetStore> stores = petStoreRepo.findAll();

        if (stores.isEmpty()) {
            System.out.println("Aucune animalerie trouvée.");
            return;
        }

        PetStore targetStore = stores.get(0);
        System.out.println("\n=== Animaux de l'animalerie : " + targetStore.getName() + " ===");

        List<Animal> animals = animalRepo.findByPetStore(targetStore.getId());
        if (animals.isEmpty()) {
            System.out.println("  (aucun animal)");
        } else {
            animals.forEach(a -> System.out.println("  - " + a));
        }
    }

    private static Date date(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
