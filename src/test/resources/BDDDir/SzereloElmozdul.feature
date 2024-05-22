Feature: A szerelő egy mezőről egy szomszédosra lép

  A csöveken egyszerre csak egy szerelő tartózkodhat, a többi mezőn tetszőleges számú

  Scenario Outline: A szerelő a mezőről, ahol áll, egy szomszédos mezőre mozdul át.
    Given Egy egyszerű pálya
    When Egy "<moveto>" szomszédos csőre lép
    Then A csőre kell kerülnie, ha az nem foglalt
    And El kell tűnnie a korábbi mezőről
    Examples:
    | moveto  |
    | cs2     |
    | cs3     |