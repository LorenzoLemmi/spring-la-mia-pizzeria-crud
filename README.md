# Pizzeria Management Application

Applicazione web full-stack sviluppata con **Java e Spring Boot** per la gestione di una pizzeria.  
Il progetto consente di amministrare pizze, ingredienti e offerte speciali, con controllo degli accessi tramite ruoli utente.

## 🚀 Tecnologie utilizzate
- Java
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- MySQL
- Thymeleaf
- Bootstrap
- Maven

## 📌 Funzionalità principali

### Gestione Pizze
- CRUD completo delle pizze (creazione, visualizzazione, modifica, eliminazione)
- Visualizzazione elenco pizze con pagina di dettaglio
- Ricerca delle pizze per titolo (filtro lato server)
- Validazione dei dati lato server:
  - campi obbligatori
  - lunghezza massima dei testi
  - prezzo con valore valido

### Offerte Speciali
- Associazione di offerte speciali alle pizze
- Relazione **1:N** (una pizza può avere più offerte)
- Gestione delle offerte con:
  - data di inizio
  - data di fine
  - titolo

### Ingredienti
- Gestione degli ingredienti
- Relazione **N:N** tra pizze e ingredienti
- Possibilità di associare uno o più ingredienti a una pizza

### Autenticazione e Autorizzazione
- Sistema di autenticazione con utenti salvati a database
- Ruoli supportati:
  - **USER**: accesso alla lista e al dettaglio delle pizze
  - **ADMIN**: accesso completo a tutte le funzionalità
- Protezione delle pagine tramite Spring Security

### Interfaccia Utente
- UI server-side con Thymeleaf
- Utilizzo di **fragments** per componenti riutilizzabili
- Layout responsive con Bootstrap

## 🧱 Architettura
L’applicazione segue una classica architettura a livelli:
- Controller
- Service
- Repository
- Database

## ▶️ Avvio del progetto
1. Clonare il repository
2. Configurare il database MySQL
3. Aggiornare le credenziali nel file `application.properties`
4. Avviare l’applicazione tramite Spring Boot
