# APT-NS
# Microservices Project: Music Store API

Dit project bestaat uit drie microservices: **Album**, **Artiest** en **Winkel**, en een **React front-end**. Het doel is om een music store systeem op te zetten waar gebruikers albums kunnen bekijken, artiesten kunnen ontdekken en winkels kunnen zien die albums op voorraad hebben. De back-end is gebouwd met microservices en de front-end is een React applicatie.

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
- `GET /albums`: Haalt een lijst van alle albums op.
- `GET /albums/{id}`: Haalt een specifiek album op via zijn ID.
- `POST /albums`: Voegt een nieuw album toe.
- `PUT /albums/{id}`: Werk een album bij.
- `DELETE /albums/{id}`: Verwijder een album.

#### Artiest Service
- `GET /artiesten`: Haalt een lijst van alle artiesten op.
- `GET /artiesten/{id}`: Haalt een specifieke artiest op via zijn ID.
- `POST /artiesten`: Voeg een nieuwe artiest toe.

#### Winkel Service
- `GET /winkels`: Haalt een lijst van alle winkels op.
- `GET /winkels/{id}`: Haalt een specifieke winkel op via zijn ID.
- `POST /winkels`: Voeg een nieuwe winkel toe.

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


