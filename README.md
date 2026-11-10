# CLI-Aufgabenverwaltung

Eine kleine Aufgabenverwaltung für die Kommandozeile, geschrieben in Java.
Aufgaben werden in einer CSV-Datei gespeichert und beim nächsten Start wieder geladen.

## Funktionen

- Aufgaben anlegen (Titel, Beschreibung, Priorität)
- Alle Aufgaben auflisten
- Status ändern (offen, in Bearbeitung, erledigt)
- Priorität ändern (niedrig, mittel, hoch)
- Aufgaben löschen
- Automatisches Speichern in `Daten/Aufgaben.csv`

## Voraussetzungen

- Java 21 oder neuer

## Starten

In IntelliJ: `Main.java` öffnen und ausführen.

Über die Kommandozeile:

```
javac -d out components/*.java
java -cp out Main
```

## Aufbau

| Klasse | Aufgabe |
|---|---|
| `Task` | Eine einzelne Aufgabe mit ID, Titel, Beschreibung, Status und Priorität |
| `TaskManager` | Verwaltet die Liste der Aufgaben, vergibt IDs, liest und schreibt die CSV |
| `Main` | Menü und Benutzereingaben |

## Was ich dabei geübt habe

- Klassen und Enums
- Collections (`ArrayList`) und Lambdas (`removeIf`)
- Datei-I/O mit `java.nio.file`
- Fehlerbehandlung bei Nutzereingaben und beim Speichern
