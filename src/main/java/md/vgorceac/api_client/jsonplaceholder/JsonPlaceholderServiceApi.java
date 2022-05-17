package md.vgorceac.api_client.jsonplaceholder;

import md.vgorceac.dto.Photo;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface JsonPlaceholderServiceApi {
    @GET("/photos")
    Call<List<Photo>> getPhotos();
}
