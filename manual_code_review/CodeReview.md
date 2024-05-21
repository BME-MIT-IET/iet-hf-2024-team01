A SonarCloud kód analízisének eredménye:

![img_1.png](img_1.png)

Ez alapján feltételeztük, hogy a legfontosabbak, amelyeket mindenképp javítanunk kell:
- A ***Software Quality*** kategóriák közül a **Reliability** típusba esnek
- A ***Severity*** kategóriák közül a **High** típusba esnek

Kiválasztva a **__Reliability__** Issue-kat, kiderült, hogy mindkettő **High** **_Severity_**-vel rendelkezik

A két issue nagyban hasonlított egymásra:

![img_2.png](img_2.png)

![img_3.png](img_3.png)

Mindkettőt a try-with-resources funkcionalitással oldottuk meg. Ennek során egy try blokkbe tettük a Reader/Writer objektumok használatát az alábbi módon:

![img_4.png](img_4.png)