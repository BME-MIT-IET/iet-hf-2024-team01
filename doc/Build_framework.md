# <p style="text-align: center;"> Build setup </p>
## Build keretrendszer 
### Keretrendszer kiválasztása
Először felmértük az opciókat, hogy mely keretrendszerek lennének alkalmasak a projekthez, mivel eddig nem volt semmilyen beüzemelve ehhez a munkához. Végül leszűkítettük az opciókat a két általunk ismertebbre a Maven-re és Gradle-re, mivel a Gradle nagyobb projektekhez javaslott és a Maven a kisebbekhez, így végül az utóbbi mellett döntöttünk.

### Keretrendszer beüzemelése
Miután a Maven-re jutott a döntés, a peojekt fájljait  annak megfelelően átstruktúráltuk és hozzáadtuka pom.xml fájlt felparaméterezve, ami a buildeléshez szükséges.

## Continuous Integration
Mivel ez sem volt beüzemelve, ezért a lehetőségek közül az általunk már megismert GitHub Actions-t választottuk.  

A gyakorlaton tanultakhoz hasonlóan létrehoztuk a "Java with Maven" konfigurációból, amelyet a projektnek megfelelően módosítottunk.

A Java verziót frissítettük, hogy egyezzen a projektben használt verzióval.
