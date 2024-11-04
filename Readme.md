Exercice : Déploiement d'un Système Multi-Services avec Nginx Ingress et Auto-Scaling dans Kubernetes
# Objectif

Créer un environnement Kubernetes où une application composée de trois microservices backend (développés avec Spring Boot) et d’un frontend (développé avec Angular) est déployée. Utiliser Nginx Ingress pour router le trafic entre ces services et mettre en place un auto-scaling pour répondre automatiquement à la charge.

## Architecture et Noms des Composants

### Microservices Spring Boot

- **User Service (user-service)** : Gère les informations et profils utilisateurs.
- **Product Service (product-service)** : Gère les produits.
- **Order Service (order-service)** : Gère les commandes.

Chaque microservice doit être déployé dans son propre pod, avec un service de type ClusterIP pour la communication interne dans le cluster.

### Frontend Angular

- **Angular Frontend (frontend)** : Interface utilisateur pour interagir avec les microservices. Ce frontend doit être configuré pour consommer les API exposées par chacun des trois microservices.

Le frontend sera accessible via la racine (/) à l’aide de Nginx Ingress.

## Étapes Détailées de l'Exercice

1. **Déploiement des Microservices Backend Spring Boot**
    - Pour chaque microservice (User Service, Product Service, Order Service), configure les noms des pods et des services :
        - Pod Names : `user-service-pod`, `product-service-pod`, `order-service-pod`.
        - Service Names : `user-service`, `product-service`, `order-service`.
    - Assure-toi que chaque service écoute sur le port 8080 et est accessible via un chemin spécifique :
        - `user-service` doit être accessible via le chemin `/api/users`.
        - `product-service` via le chemin `/api/products`.
        - `order-service` via le chemin `/api/orders`.

2. **Déploiement du Frontend Angular**
    - Déploie l’application Angular avec les configurations nécessaires pour qu'elle appelle les microservices via les chemins Ingress.
        - Nom du Pod : `frontend-pod`.
        - Nom du Service : `frontend`.
    - Ce service Angular sera exposé à la racine de l’Ingress (/) pour permettre aux utilisateurs d’accéder facilement au frontend.

3. **Configuration de l’Ingress pour le Routage du Trafic**
    - Crée une ressource Ingress pour router le trafic HTTP et HTTPS en fonction des chemins et de l’hôte virtuel.
    - Utilise les noms de services définis précédemment (`user-service`, `product-service`, `order-service`, `frontend`) pour spécifier les routes de chaque microservice et du frontend.
    - Règles Ingress :
        - `http://app.local/api/users` pour router vers `user-service`.
        - `http://app.local/api/products` pour router vers `product-service`.
        - `http://app.local/api/orders` pour router vers `order-service`.
        - `http://app.local/` pour router vers le service `frontend`.
    - Ajoute une règle d’hôte virtuel `app.local` pour faciliter l’accès et centraliser le routage sous un seul nom de domaine.

4. **Mise en Place du TLS (HTTPS) pour Sécuriser le Trafic**
    - Configure l’Ingress pour rediriger tout le trafic HTTP vers HTTPS.
    - Utilise un certificat auto-signé pour le domaine `app.local` et associe-le à l’Ingress pour sécuriser l’accès aux microservices et au frontend.
    - Ajoute une règle pour forcer la redirection de tout le trafic vers HTTPS afin d'assurer un accès sécurisé à toutes les parties de l’application.

5. **Auto-Scaling des Microservices Backend avec HPA (Horizontal Pod Autoscaler)**
    - Configure un Horizontal Pod Autoscaler (HPA) pour chaque microservice Spring Boot pour gérer la charge de manière dynamique :
        - **User Service (user-service)** :
            - Minimum : 1 pod, Maximum : 5 pods, Scalable en fonction de l’utilisation CPU (par exemple, au-delà de 50 % d’utilisation).
        - **Product Service (product-service)** :
            - Minimum : 1 pod, Maximum : 5 pods, Scalable en fonction de l’utilisation CPU (par exemple, au-delà de 60 % d’utilisation).
        - **Order Service (order-service)** :
            - Minimum : 1 pod, Maximum : 5 pods, Scalable en fonction de l’utilisation CPU (par exemple, au-delà de 70 % d’utilisation).
    - Assure-toi que le cluster est configuré pour capturer les métriques de performance (installation du `metrics-server` si nécessaire) pour permettre à l’HPA de fonctionner.

6. **Mise en Place d'un Scaling Basé sur la Charge pour le Frontend Angular**
    - Implémente un HPA également pour le frontend Angular (`frontend`) pour répondre aux pics de trafic utilisateur :
        - Minimum : 1 pod, Maximum : 3 pods, Scalable en fonction de l’utilisation CPU.
    - Configure l’Ingress pour distribuer uniformément le trafic entre les pods du frontend lors des montées en charge.

7. **Tests de Fonctionnement et Vérification**
    - **Accès aux Services** : Modifie le fichier `hosts` local pour que `app.local` pointe vers l’IP de l’Ingress Controller. Cela permettra de tester le routage Ingress.
    - **Test du Frontend** : Accède à `http://app.local/` pour vérifier que l’interface utilisateur Angular est accessible et peut interagir avec chaque microservice.
    - **Test des Endpoints de Microservices** : Vérifie que chaque chemin spécifique (`/api/users`, `/api/products`, `/api/orders`) redirige correctement vers les microservices correspondants.
    - **Simulation de Charge** : Envoie une charge de requêtes (à l’aide d’outils de test de charge) pour vérifier que les pods des microservices se scalent automatiquement en fonction de l’utilisation CPU.
    - **Vérification du TLS** : Accède aux URL en HTTPS pour t'assurer que toutes les connexions sont sécurisées.

8. **Monitoring et Logs pour Suivre le Comportement du Système**
    - **Logs Nginx Ingress** : Analyse les logs du contrôleur Nginx Ingress pour vérifier le routage et détecter d'éventuels problèmes.
    - **Tableau de Bord de Monitoring** : Mets en place un tableau de bord de monitoring (ex : Grafana) pour suivre les métriques des pods, du trafic, et du scaling des services. Assure-toi que les montées en charge sont correctement visualisées et que l’auto-scaling répond bien.

## Résultats attendus

À la fin de cet exercice, tu auras :

- Configuré un environnement Kubernetes multi-services avec un frontend Angular et trois microservices Spring Boot.
- Utilisé Nginx Ingress pour gérer le routage de trafic HTTP/HTTPS avec TLS vers chaque service.
- Implémenté l’auto-scaling dynamique des pods de chaque microservice et du frontend pour répondre à la charge de trafic.
- Configuré un système de monitoring pour suivre le comportement du système en temps réel.