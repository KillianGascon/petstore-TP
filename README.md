# PetStore – TP Eval JPA / Hibernate

**EPSI B3 Dev – 2025-2026**

> Auteur : *GASCON Killian*

---

## Description

Application Java multi-couches démontrant le mapping ORM avec **JPA / Hibernate 6** sur une animalerie :

- Héritage JOINED : `Animal` → `Fish`, `Cat`
- Relations bidirectionnelles : `PetStore` ↔ `Product` (ManyToMany), `PetStore` ↔ `Animal` (OneToMany)
- Seedage de la base et requête JPQL pour extraire les animaux d'une animalerie donnée

---

## Prérequis

| Outil | Version minimale |
|-------|-----------------|
| Java  | 17              |
| Maven | 3.8+            |

---

## Base de données

Le projet utilise **H2 en mode fichier** (aucune installation requise).
Le fichier `petstore.mv.db` est créé automatiquement à la racine du projet au premier lancement.
Le schéma est généré automatiquement par Hibernate (`hbm2ddl.auto=update`).

---

## Lancer l'application

```bash
mvn compile exec:java
```

L'exécution va :
1. Créer/mettre à jour le schéma en base
2. Insérer 3 animaleries, 3 produits et 6 animaux
3. Afficher tous les animaux de la première animalerie

---

## Structure du projet

```
src/main/java/fr/epsi/b32526/
├── Main.java                        # Point d'entrée
├── entity/
│   ├── enums/
│   │   ├── ProdType.java            # FOOD | ACCESSORY | CLEANING
│   │   └── FishLivEnv.java          # FRESH_WATER | SEA_WATER
│   ├── Address.java
│   ├── Product.java
│   ├── PetStore.java
│   ├── Animal.java                  # Classe abstraite (racine héritage JOINED)
│   ├── Fish.java
│   └── Cat.java
└── repository/
    ├── AddressRepository.java
    ├── ProductRepository.java
    ├── PetStoreRepository.java
    └── AnimalRepository.java        # inclut findByPetStore(Long)

src/main/resources/META-INF/
└── persistence.xml
```

---

## Schéma généré

| Table              | Rôle                                    |
|--------------------|-----------------------------------------|
| `address`          | Adresses des animaleries                |
| `petstore`         | Animaleries                             |
| `product`          | Produits en vente                       |
| `petstore_product` | Table de jointure ManyToMany            |
| `animal`           | Table de base pour l'héritage           |
| `fish`             | Données spécifiques aux poissons        |
| `cat`              | Données spécifiques aux chats           |
