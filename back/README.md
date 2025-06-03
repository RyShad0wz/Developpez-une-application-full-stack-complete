# MDD

# Présentation générale

Ce dépôt contient le code de l’application fullstack MDD, développé avec Angular, TS et en Java avec Spring Boot pour le back-end.

# Installation et lancement Back-end

Clonez le dépôt GitHub sur votre machine.

git clone https://github.com/RyShad0wz/Developpez-une-application-full-stack-complete
cd backend

Dans le fichier « src/main/resources/application.properties », vérifiez la configuration suivante : 

• Base de données MySQL :

spring.datasource.url=jdbc:mysql://localhost:3306/mddapi_db?createDatabaseIfNotExist=true&serverTimezone=UTC

spring.datasource.username=${DB_USERNAME}

spring.datasource.password=${DB_PASSWORD} 

• Hibernate (création/mise à jour automatique des tables) :

spring.jpa.hibernate.ddl-auto=update

spring.jpa.properties.hibernate.show_sql=true

spring.jpa.open-in-view=false

• Clé de signature JWT :

jwt.signing.key=${JWT_SIGNING_KEY}

Ajoutez les variables d'environnement dans les parametres Windows

Lancez l’application en exécutant la classe principale (MddapiApplication) depuis votre IDE ou via Maven.

L’application démarre sur le port 8080.

# Installation de la base de données

Si nécessaire, créez la base de données « mddapi_db » avec MySQL. 

(La propriété createDatabaseIfNotExist=true permet de la créer automatiquement si elle n’existe pas.) 

Les entités définies dans le code (USERS, ARTICLES, TOPICS, etc.) seront créées/mises à jour automatiquement grâce à Hibernate.