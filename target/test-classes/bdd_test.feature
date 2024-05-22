Feature: Valami egy

  Scenario Outline: egy az egy
    Itt "<given>"
    itt valami megy
    itt "<given>" kell legyen "<answer>" heyett

  Examples:
      | given            | answer |
      | 1                | 1   |
      | 2                | 2   |
      | 3                | 3   |