Feature: Cső csúszosság tesztelés
  Scenario Outline: Cső csúszosság tesztelés
    Given A cső <hány> körig csúszós
    When Elteilk egy kör
    Then A cső "<csúszóssága>"

    Examples:
      | hány    | csúszóssága |
      |  0      | nem csúszós |
      |  5      | csúszós     |
      |  1      | nem csúszós |