Feature: Pumpa javítás tesztelés
  Scenario Outline: Pumpa javítás tesztelés
    Given Egy pumpa, amibe megy be egy működő cső, de a pumpa nem működik
    When Ha a pumpát megjavítjuk folyni fog a víz, ha nem akkor nem fog. Javitunk: "<javit>"
    Then A kimeneti csövön, a víz folyni fog: "<folyik>"

    Examples:
      |   javit  | folyik |
      |   true   |  true  |
      |  false   |  false |