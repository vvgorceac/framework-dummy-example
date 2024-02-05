Feature: Dummy Example

  @allure.id:1
  @allure.label.story:Dummy_Story
  Scenario: Photo endpoint should provide a list of photos - 1
    Given user sends a requests for list of photos
    Then user receives a valid response
    And user removes all photos that have 'albumId' different than 100
    And user removes all photos that do not contain word 'error' in the title


  @allure.id:12
  @allure.label.story:Dummy_Story
  Scenario: Photo endpoint should provide a list of photos - 2
    Given user sends a requests for list of photos
    Then user receives a valid response
    And user removes all photos that have 'albumId' different than 1001
    And user removes all photos that do not contain word 'error' in the title


  @allure.id:13
  @allure.label.story:Dummy_Story
  Scenario: Photo endpoint should provide a list of photos - 3
    Given user sends a requests for list of photos
    Then user receives a valid response
    And user removes all photos that have 'albumId' different than 1003
    And user removes all photos that do not contain word 'error' in the title

