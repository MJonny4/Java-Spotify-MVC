# P1-T8-MunteanuIon

+ Programa Filtre per TIPUS, ACTIU I TITOL(KeySensitive)
+ Programa por veure els detalls del producte clicant a sobre la taula
  - Si cliques mes d'una fila, et sortira un error. Desel·lecciona la fila automaticament.

+ Programa pot afegir un nou producte si no hi ha cap fila seleccionada
  + Et preguntar a el tipus de producte que vols afegir
    - No pots afegir canconcs a album o llista si s'estan creant
  + Hi han dos botons Cancelar i Acceptar (us obvi no cal explicar)

+ Programa pot editar un producte si hi ha una fila seleccionada
  + Els camps pasaran a ser editables
  + Hi han dos botons Cancelar i Acceptar (us obvi no cal explicar)
  + Es pot editar Contingut si es Album o Llista
    + Hi han dos ComboBox amb els productes disponibles i amb els ja utilitzats per poder eliminar-los
    + Hi ha un botó per afegir un nou producte a la llista
    + Hi ha un botó per eliminar un producte de la llista

+ Programa pot eliminar un producte si hi ha una fila seleccionada
  + NO ES POT ELIMINAR: 
    - CANCO ES PART DE ALBUM O LLISTA O TE REPRODUCCIONS
    - ALBUM TE CONTINGUT O ES PART DE LLISTA
    - LLISTA TE CONTINGUT

+ Com SQLDeveloper pots fer commit y rollback
  + Si fas commit, es guardaran els canvis a la base de dades
  + Si fas rollback, es cancelaran els canvis a la base de dades
  + Si surts dels programa et preguntara si vols fer commit o rollback
    - Si tanques el programa no fara commit i tancara la connexio amb la base de dades