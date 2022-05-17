package md.vgorceac.step_actions.api;

import md.vgorceac.api_client.ApiProvider;
import md.vgorceac.api_client.jsonplaceholder.JsonPlaceholderServiceApi;
import md.vgorceac.config.ServicesConfig;
import md.vgorceac.context.Keys;
import md.vgorceac.context.ScenarioContext;
import md.vgorceac.dto.Photo;
import io.qameta.allure.Allure;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Service
@Log4j2
@AllArgsConstructor
public class PhotoActionSteps {
    private final ApiProvider apiProvider;
    private final ScenarioContext scenarioContext;
    private final ServicesConfig servicesConfig;


    @SneakyThrows
    public void getPhotos() {
        String baseUrl = servicesConfig.getJsonPlaceholderService().getUrl();

        JsonPlaceholderServiceApi jsonPlaceholderServiceApi = apiProvider.get(JsonPlaceholderServiceApi.class, baseUrl);
        Response<List<Photo>> response = jsonPlaceholderServiceApi.getPhotos().execute();

        scenarioContext.save(Keys.GET_PHOTO_RESPONSE, response);
    }

    public void photoResponseShouldBeOk() {
        Response<List<Photo>> response = scenarioContext.get(Keys.GET_PHOTO_RESPONSE, Response.class);

        assertThat("Response code should be in range 200..300", response.isSuccessful(), is(true));
        assertThat("Response should contain more that 0 photos", response.body(), iterableWithSize(greaterThan(0)));
        assertThat("At least one photo should have 'non sit quo' title",
                response.body(),
                hasItem(hasProperty("title", is("non sit quo"))));

        scenarioContext.save(Keys.PHOTOS, response.body());
        Allure.attachment("Photo Response:", response.toString());
    }

    public void filterPhotosByPredicate(Predicate<Photo> predicate) {
        List<Photo> photos = scenarioContext.get(Keys.PHOTOS, List.class);
        List<Photo> filteredPhotos = photos.stream()
                .filter(predicate)
                .collect(Collectors.toList());

        scenarioContext.save(Keys.PHOTOS, filteredPhotos);
        Allure.addAttachment("Context photos: ", filteredPhotos.toString());
    }

}
