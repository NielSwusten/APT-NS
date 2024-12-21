# APT-NS
# Microservices Project: Music Store API

Dit project bestaat uit drie microservices: **Album**, **Artiest** en **Winkel**, en een **React front-end**. Het doel is om een music store systeem op te zetten waar gebruikers albums kunnen bekijken, artiesten kunnen ontdekken en winkels kunnen zien die albums op voorraad hebben. De back-end is gebouwd met microservices en de front-end is een React applicatie.

Uitgewerkte punten
1. ❔ ALGEMENE EISEN & DOCUMENTATIE (alles samen +60% op Project)
   
2.1 Maak een front-end voor je applicatie (ook in container). (+15%)
   
## Overzicht van het project

De applicatie bestaat uit de volgende onderdelen:
1. **Album Service**: Beheert albums (liedje) en zijn details.
2. **Artiest Service**: Beheert artiesten en hun informatie.
3. **Winkel Service**: Beheert winkels en hun albums.
4. **Gateway**: Een API Gateway die alle microservices bij elkaar brengt en beveiligt via OAuth2.
5. **React Front-end**: Toont de gegevens van de microservices aan de gebruiker.

## Architectuur

De microservices communiceren via REST API's en worden beheerd door een API Gateway die beveiliging via OAuth2 implementeert. De backend maakt gebruik van **MongoDB** voor de Album service en **SQL** voor de Artiest en Winkel services. 

### Microservices
- **Album Service**: Deze service beheert albums en hun metadata (bijv. titel, artiest, releasedate). De data wordt opgeslagen in een SQL database.
- **Artiest Service**: Deze service beheert artiesten en hun gegevens (naam, bio, etc.). De data wordt opgeslagen in een MongoDB database.
- **Winkel Service**: Deze service beheert de winkels en hun voorraad van albums. De data wordt opgeslagen in een SQL database.

### API Endpoints
De API is toegankelijk via de volgende endpoints:

#### Album Service
- `GET /album`: Haalt een lijst van alle albums op.
  ![image](https://github.com/user-attachments/assets/3e4f5990-e8a9-4167-9b9f-2b46bb265b1a)
- `GET /album/{id}`: Haalt een specifiek album op via zijn ID.
  ![image](https://github.com/user-attachments/assets/d0b72bc8-4f99-4383-a751-aa64cd21f7e7)
- `POST /album`: Voegt een nieuw album toe.
  ![image](https://github.com/user-attachments/assets/ae990ba4-70bf-4e75-b201-badcb53cafd4)
- `PUT /album/{id}`: Werk een album bij.
  ![image](https://github.com/user-attachments/assets/d9fd463c-6a5c-42d7-aecf-beb89c5f98a5)
- `DELETE /album/{id}`: Verwijder een album.
  ![image](https://github.com/user-attachments/assets/571d40de-dcc4-4a27-9693-822d08efda2b)

#### Artiest Service
- `GET /artiest`: Haalt een lijst van alle artiesten op.
  ![image](https://github.com/user-attachments/assets/6a718cfe-18f0-4bcb-9d2c-4545bf897b2b)
- `GET /artiest/{id}`: Haalt een specifieke artiest op via zijn ID.
  ![image](https://github.com/user-attachments/assets/2818975a-d7ab-4bed-9606-e980710ab4ce)
- `POST /artiest`: Voeg een nieuwe artiest toe.
  ![image](https://github.com/user-attachments/assets/85a19807-d06a-4afe-b799-9062732d8d67)


#### Winkel Service
- `GET /winkel`: Haalt een lijst van alle winkels op.
  ![image](https://github.com/user-attachments/assets/8c6ed718-02f5-4491-8e69-424cc60603af)
- `GET /winkel/{id}`: Haalt een specifieke winkel op via zijn ID.
  ![image](https://github.com/user-attachments/assets/47048f35-a4db-4bc6-8729-7985aef0e685)  
- `POST /winkel`: Voeg een nieuwe winkel toe.
  ![image](https://github.com/user-attachments/assets/c3ef3d59-091b-4af4-8ce9-f5107e784e6a)

### API Gateway
De API Gateway verzorgt de routing van verzoeken naar de juiste microservice en zorgt voor de beveiliging via **OAuth2**. De GET verzoeken zijn niet beveiligd, maar de andere verzoeken (POST, PUT, DELETE) vereisen authenticatie via OAuth2.

### Databases
-SQL: Gebruikt voor de Album Service en Winkel Service om respectievelijk album- en winkelgegevens op te slaan, vanwege de gestructureerde aard van deze data en de onderlinge relaties.

-MongoDB: Gebruikt voor de Artiest Service om artiestgegevens op te slaan, aangezien deze vaak semi-gestructureerd zijn en MongoDB meer flexibiliteit biedt voor dergelijke data.

## Authenticatie
De API Gateway maakt gebruik van **OAuth2** voor authenticatie. Alleen gebruikers met een geldig OAuth2 token kunnen de POST, PUT en DELETE verzoeken uitvoeren. De GET verzoeken zijn openbaar toegankelijk.

## Front-end
De front-end van de applicatie is gebouwd in **React** en communiceert met de backend via de API Gateway. De front-end toont gegevens over albums, artiesten en winkels, en laat ingelogde gebruikers albums toevoegen.

## Gehoste Versies
- **Artiest Service**: [http://localhost:8080/artiest](http://localhost:8080/artiest)
- **Winkel Service**: [http://localhost:8080/winkel](http://localhost:8080/winkel)
- **Album Service**: [http://localhost:8080/album](http://localhost:8080/album)
- **React Front-end**: [http://localhost:3000/](http://localhost:3000/)


