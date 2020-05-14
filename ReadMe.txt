Semesteroppgave i progutvikling

Merk 1:
Systemet har to ulike filbehandlinger for user og admin

Admin: Her saver og åpner til Componentregisteret som fyller informasjon i tabellen i denne viewen,
men den fyller også Choiceboxen i userview. Det vil si når du åpner og lagrer fil i denne viewen vil
du lagre/åpne med Componenter.

User: Her saver og åpner filer til Dataregisteret som fyller tabellen i denne Viewen. Her vil du lagre/
åpne Data.

Begge Viewene støtter Jobj som binær og txt som tekstfil.

Merk 2:
AdminController styrer også deler av userController, men ikke motsatt. Dvs det du gjør i AdminController
har noe å si i UserController siden den bruker Registeret i AdminController for å vise informasjon
i choiceboksene.

Merk 3:
Systemet har gjennbrukt koder og fremgangsmåte fra undervisning i Programutvikling.

Merk 4:
MainApp er startfilen i programmet.

Merk 5:
Programmet kan kjøres med app:MainApp eller Maven:Semesteroppgave

Merk 4:
Mappestructuren
├───src
│   └───main
│       ├───java
│       │   └───programutvikling
│       │       ├───base
│       │       ├───controllers
│       │       │   ├───controllersHelper
│       │       │   ├───controllersMain
│       │       │   ├───exception
│       │       │   └───helpers
│       │       └───fileHandler
│       └───resources
│           └───programutvikling
│               ├───controllers
│               └───fxml
└───target
    ├───classes
    │   └───programutvikling
    │       ├───base
    │       ├───controllers
    │       │   ├───controllersHelper
    │       │   └───controllersMain
    │       ├───fileHandler
    │       └───fxml
    ├───generated-sources
    │   └───annotations
    ├───maven-archiver
    └───maven-status
        └───maven-compiler-plugin
            └───compile
                └───default-compile