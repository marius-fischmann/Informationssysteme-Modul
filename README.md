# Beispielprojekt mit Java, SpringBoot und Gradle

Informationssysteme II, Bachelor Wirtschaftsinformatik

Stefan Sarstedt, stefan.sarstedt(at)haw-hamburg.de  
Sven Berding, sven.berding(at)haw-hamburg.de

## A. Einrichtung des JDK und Ausführen der Tests

1. Installiere lokal auf Deinem Rechner:
    - Java OpenJDK (nicht die JRE! Mindestens das **JDK 8**, erfolgreich getestet auch mit JDK 13, das Projekt funktioniert **nicht** mit dem aktuellen JDK 16!): https://openjdk.java.net. Unter Windows bedeutet dies u.a.: JAVA_HOME setzen und den Compiler in den PATH aufnehmen ([Anleitung hier](https://tecadmin.net/set-java-home-on-windows/)); bei Änderungen der Systemeinstellungen muss ein Terminal neu geöffnet werden, damit die Änderungen effektiv werden.
    
    
2. Forke dieses Projekt (nutze den `Fork`-Button oben rechts).

3. Trage uns (Sven Berding und mich) unter `Settings->Members` als Projektmitglieder mit der Berechtigung `Maintainer` ein.

4. Klone Dein geforktes Projekt: 
    ```bash
    git clone https://git.haw-hamburg.de/<Dein-geforktes-Projekt>
    ```

5. Öffne ein Terminal.

6. Prüfemittels `javac --version` (vergiss das "c" nicht!), ob Du das korrekte JDK verwendest! Falls nicht, achte auf die korrekte Einrichtung des JDK (Punkt 1).

7. Führe die Tests im Terminal aus mittels 
     ```bash
     ./gradlew build (unter Linux/macOS, bei Bedarf dort zuvor "chmod +x ./gradlew" ausführen, um die Ausführungsberechtigung zu setzen)
     ```
     bzw. 
     ```bash
     gradlew.bat build (unter Windows)
     ```
     Gradlew/Gradle ist ein Tool zur Automatisierung (ähnlich `maven`, `make`, `npm`, `msbuild`, ...) und übersetzt das Projekt, führt die Tests aus und erzeugt eine Jar-Datei aus den Quellen. Informationen zu gradle findest Du [hier](https://gradle.org). Wesentlich ist die Datei `build.gradle`, in der die Projektabhängigkeiten zu externen Bibliotheken und Tasks definiert werden. Durch das Java-Plugin stehen Tasks zur Übersetzung, Starten der Applikation, etc. zur Verfügung. Du kannst alles verfügbaren Tasks mittels `./gradlew (gradlew.bat) tasks` auflisten.

     Es sollte `Build Successful` erscheinen (falls nein, prüfe noch einmal Punkt 6 - den Fehler ` Errors occurred while build effective model from ... jaxb-osgi:2.2.10`  kannst Du ignorieren). Die erste Ausführung des Gradle-Wrappers `gradlew` dauert etwas länger, da zunächst die Gradle-Distribution und dann die abhängigen Java-Bibliotheken geladen werden (später kommen sie aus dem lokalen Cache). 

## B. Einrichtung einer IDE

1. Installiere lokal auf Deinem Rechner (achte auf die aktuellen Versionen!):
    - (empfohlen) Jetbrains IntelliJ IDEA Ultimate, aktuelle Version: https://www.jetbrains.com/idea/ (du kannst dies mit Deiner `haw-hamburg.de`-Adresse kostenlos nutzen)
        - Installiere das Lombok Plugin: https://projectlombok.org ([Anleitung hier](https://projectlombok.org/setup/intellij)).
    - (alternativ) Eclipse mit entsprechendem Lombok-Plugin
    
2. Starte IntelliJ, **aber öffne das Projekt noch nicht**!

3. Aktiviere in IntelliJ bei den Preferences unter `Editor->Code Style` auf dem Tab `Formatter Control` die Option `Enable formatter markers in comments`. Falls diese Option nicht gesetzt ist, führt dies in den REST-assured-Testfällen zu unschönen Code-Reformatierungen, die das Lesen dieser Testfälle erschweren.

4. Öffne nun Dein geforktes Projekt in IntelliJ. Es dauert etwas beim ersten Laden.

5. Aktiviere bei den Preferences unter `Build,Execution,Deployment->Compiler->Annotation Processors` das Annotation Processing (`Enable annotation processing`). Hierdurch erkennt IntelliJ die erzeugten Lombok-Artefakte korrekt und erzeugt keine Warnungen/Fehler mehr im Editor aufgrund (fälschlich) "fehlender" Getter/Setter.

6. Setze bei den Preferences unter `Build,Execution,Deployment->Build Tools->Gradle` die Optionen `Build an run using` und `Run tests using` auf `Gradle`.

7. Setze unter `File->Project Structure` das JDK (Option `Project Settings->Project`) auf deine JDK-Version (z.B. `1.8` für das JDK 8).

8. Öffne ein Terminal in IntelliJ (`View->Tool Windows->Terminal` im Menü). Führe die Tests wie unter A.7 beschrieben hier im Terminal aus.

9. Du kannst auch die Tests durch die IDE ausführen lassen. Gehe dazu mit der rechten Maustaste auf Dein Projekt und wähle `Run Tests in in2lab`.
