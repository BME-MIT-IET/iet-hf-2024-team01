Feature: Szerelő megpróbál egy pumpát felvenni
  Scenario Outline: Szerelő megpróbál egy pumpát felvenni
    Given A szerelő egy "<mező>" mezőn áll
    When Megpróbál egy pumpát felvenni
    Then A pumpa "<van_vagy_nincs>" a szerelőnél
    Examples:
    | mező    | van_vagy_nincs |
    |pumpa    | nincs          |
    |cső      | nincs          |
    |ciszterna| van            |
    |forrás   | nincs          |
