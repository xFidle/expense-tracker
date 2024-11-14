## Backend
### Zakładanie kont użytkowników
- Rejestracja.
- Obsługa haseł.
- Możliwość edycji i usuwania konta.

### System logowania
- Logowanie przy użyciu loginu i hasła.

### Baza danych z użytkownikami
- Dane użytkownika: imię, login, data utworzenia konta.

### Baza danych z wydatkami
- Przechowywanie danych o wydatkach: nazwa, kwota, kategoria, data, opis.
- Możliwość dodawania wydatków cyklicznych.
- Relacja użytkownik-wydatek.

### Pobieranie danych o wydatkach z bazy
- API REST do pobierania wydatków w formie paginowanych list.
- Wsparcie filtrowania według daty, kategorii, kwoty.

### Grupowanie wydatków
- Kategoryzacja wydatków (np. jedzenie, transport, rozrywka).
- Tworzenie niestandardowych kategorii przez użytkownika.

### Statystyki o wydatkach
- Wykresy miesięcznych wydatków.
- Porównania wydatków w czasie.
- Analiza procentowa wydatków w poszczególnych kategoriach.


## Frontend
### Wyświetlanie danych
- Intuicyjny interfejs użytkownika z wykresami i tabelami.
- Sortowanie i filtrowanie danych w czasie rzeczywistym.

### Formularz do dodawania wydatków
- Pola do wprowadzania nazwy, kwoty, kategorii i opisu.
- Możliwość dodawania wydatków cyklicznych.

### Menu główne
- Nawigacja między ekranami: wydatki, statystyki, ustawienia.

### Ekran główny
- Szybki podgląd wydatków: ostatnie wydatki, bilans miesięczny.
- Przycisk dodawania wydatku.

### Ekran ustawień
- Edycja profilu użytkownika.
- Zarządzanie kategoriami wydatków.

### Dodatkowe Funkcje do Rozważenia
- Powiadomienia.
- Tryb offline (przechowywanie lokalne w IndexedDB/SQLite).
- Funkcja budżetowania miesięcznego.


## Technologie
### Frontend
- React.js.
- Kotlin Multiplatform (opcja alternatywna).

### Backend
- **Spring Boot**:
  - Framework do szybkiego budowania aplikacji w języku Java.
  - Moduły:
    - Spring Security (autoryzacja, szyfrowanie).
    - Spring Data JPA (zarządzanie bazą danych).
    - Spring Web (API REST).

### Baza danych
- **Oracle Database**:
  - Relacyjna baza danych zapewniająca wydajność i skalowalność.
  - Obsługa procedur przechowywanych (stored procedures) i indeksowania.


## Podział pracy
- **Backend**: Michał Suski, Kamil Marszałek.
- **Frontend**: Damian D’Souza.
- **Baza danych**: Michał Szwejk.

## Przykładowy interfejs

<p align="center">
  <img src="prototype/prototype.png" alt="Prototyp interfejsu">
</p>

