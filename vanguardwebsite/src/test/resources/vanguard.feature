Feature: personal investor module comparision test

  @Test
  Scenario: compare purchase and redemption fee in fund details page with that of purchase and redemption fees page
    Given public user launches vanguard investor site
    And navigate to personal investor site
    When the user navigate to purchase & redemption fees page
    And get details of the funds with respective purchase & redemption fees
      | VGAVX |
      | VGRLX |
      | VGXRX |
      | VICSX |
      | VIHAX |
    Then they verify purchase fee & redemption fee in Fees & minimum tab matches with that of purchase & redemption fees page
