Feature: Dummy Example

  @allure.id:1
  @allure.label.story:Dummy_Story
  Scenario: Photo endpoint should provide a list of photos
    Given user sends a requests for list of photos
    Then user receives a valid response
    And user removes all photos that have 'albumId' different than 100
    And user removes all photos that do not contain word 'error' in the title

