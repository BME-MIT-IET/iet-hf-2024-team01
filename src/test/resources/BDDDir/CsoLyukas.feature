Feature: A cső lyukasságának tesztelése
  Scenario Outline:  A cső lyukasságának tesztelése
    Given A cső jelenleg "<lyukasztahtosag>"
    When A csővet megpróbáljuk kilyuakasztani
    Then A cső ezután "<eredmeny>"
    Examples:
    | lyukasztahtosag | eredmeny  |
    | lyukas          | lyukas    |
    | nemlyukas       | lyukas    |
    | ellenallo       | nem lyukas|