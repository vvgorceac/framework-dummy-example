package md.vgorceac.step_definition.api;

import md.vgorceac.dto.Photo;
import md.vgorceac.step_actions.api.PhotoActionSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.AllArgsConstructor;

import java.util.function.Predicate;

@AllArgsConstructor
public class PhotoStepDefinition {
    private final PhotoActionSteps photoActionSteps;


    @Given("user sends a requests for list of photos")
    public void userSendsARequestForListOfPhotos() {
        photoActionSteps.getPhotos();
    }

    @Then("user receives a valid response")
    public void userReceivesAValidResponse() {
        photoActionSteps.photoResponseShouldBeOk();
    }

    @Then("user removes all photos that have 'albumId' different than {int}")
    public void userRemovesPhotoWithDifferentAlbumId(int albumId) {
        Predicate<Photo> predicate = (photo) -> photo.getAlbumId().equals(albumId);
        photoActionSteps.filterPhotosByPredicate(predicate);
    }

    @Then("user removes all photos that do not contain word {string} in the title")
    public void userRemovesPhotoWithKeywordInTitle(String keyWord) {
        Predicate<Photo> predicate = (photo) -> photo.getTitle().contains(keyWord);
        photoActionSteps.filterPhotosByPredicate(predicate);
    }
}
