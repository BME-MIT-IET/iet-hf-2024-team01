Feature: Pumpa átállítás tesztelés
  Scenario Outline: Pumpa átállítás tesztelés
    Given A pumpa bemeneti csöve: "<csoBe>" és kiementi csöve: "<csoKi>"
    When Pumpa átallítása után a bementi cső: "<csoBe>" marad és a kimeneti cső: "<csoKiAt>"
    Then A víz az átállított csőben fog folyni, ami "<csoKiFolyik>"

    Examples:
      | csoBe   | csoKi     | csoKiAt   | csoKiFolyik |
      |   1     | 2         |   3       |   3         |
      |   1     | 3         |   2       |   2         |