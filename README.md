# MDD

# Présentation générale

Ce dépôt contient le code de l’application fullstack MDD, développé avec Angular, TS et en Java avec Spring Boot pour le back-end.

# Installation et lancement Front-end

Clonez le dépot GitHub :

git clone https://github.com/RyShad0wz/Developpez-une-application-full-stack-complete

cd front

Installation des dépendences :

npm install

npm run start ou ng serve

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

# Documentation de l’API (Swagger)

La documentation interactive de l’API est fournie par Springdoc OpenAPI. • Swagger UI : http://localhost:8080/swagger-ui/index.html

• Documentation OpenAPI JSON : http://localhost:8080/v3/api-docs

Pour tester les routes sécurisées, faire une réquête register ou login, récupérer le token renvoyé et l'ajouter dans le Bearer