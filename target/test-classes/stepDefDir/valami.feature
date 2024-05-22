Feature: Valami feature
  Hosszabb leírás

  Scenario Outline: Egy az egy
    Given A szam <arg0>
    When Szamolunk
    Then A valasz "<answer>"
    Examples:
      | arg0 | answer
      | 1    | yes
      | 2    | no