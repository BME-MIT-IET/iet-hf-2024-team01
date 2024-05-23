## Behaviour Driven Development

### A használt eszköz
A lehetséges feladatok közül a BDD tesztelés illetve az erre platformot nyújtó Cucumber azért kerültek kiválasztásra, mert nem voltak számunkra ismeretlenek. A Rendszermodellezés tárgy keretein belül megismerkedtünk a fogalommal, és a szorgalmi feladat lehetőséget adott a Cucumber kipróbálására is.

### Az eszköz beépítése a projektbe
Sajnos a Cucumber használatához szükséges dependenciák felvétele sokkal több energiát és munkaórát vett igénybe, mint eredetileg gondoltuk. Az általunk használt IDE (IntelliJ Idea) több esetben nem ismerte fel az eszközhöz importált osztályokat, sőt, a StepDefinitions osztályokban (amelyek a Gherkin Syntax Java kódnak megfeleltetéséért felelnek) a projektben lévő osztályok importjait sem tudta megfelelően kezelni.
Sok munka árán ugyan, de sikerült megoldást találnunk a problémára.

### A tesztek
A BDD tesztek lényege, hogy az alkalmazás viselkedését vizsgálják, a használt eszköz pedig ennek megfogalmazását könnyíti meg nem csak a fejlesztők, hanem a (valós esetben) projektben szereplő más személyek számára is. A tesztek mindegyikére külön Feature fájlokat írtunk, és a könnyebb követhetőség érdekében minden teszt saját StepDefinitions fájllal is rendelkezik, így egy-egy teszt önmagában is végigkövethető, értelmezhető.

### Összefoglalás, tanulságok
A BDD elvek láthatóan nem arra valók, hogy egy már meglévő projektet teszteljünk ilyen módszerekkel. Hiába megoldható, sokszor érződik önmaga kifordítottjának a tesztek megírásának folyamata. Ez viszont azt is megmutatta számunkra, hogy mennyire fontos a tesztelés a projekt teljes életútja folyamán, akkor is, ha nem viselkedésvezérelt fejlesztési paradigmában dolgozunk.