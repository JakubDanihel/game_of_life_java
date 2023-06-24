## Hra zivot
Jednoducha hra zivot napisana v Java
----------------------------------------------------------------

Hra zivot je cellurat automaton vytvorena anglickym matematikom John Horton Conway v 1970. Jedna sa o zero-player game co znamena ze jej evolucia je zavysla prave od pociatocneho stavu a nepotrebuje ziadne dalsie vstupy ( https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life ).

Hra pri spusteni nahodne vygeneruje obsadenost pola kde sa nahodne vygeneruju zive bunky.
Nasledne bunky podliehaju nasledovnym pravidlam:
    1. Kazda bunka co ma menej ako 2 susedov zomrie na osamotenie.
    2. Kazda bunka z 2 alebo 3 susedmi prezije do dalsej generacie.
    3. kazda bunka s viac ako 3 zijucimi susedmi zomrie na "preludnenie".
    4. Kazda brtva bunka s presne 3 zijucimi susedmi sa stane zivou ak sa okolo nej nachadza presne 3 susedia akokeby sa narodila.



