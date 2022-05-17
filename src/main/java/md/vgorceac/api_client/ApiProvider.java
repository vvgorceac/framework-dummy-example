package md.vgorceac.api_client;

import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Service
public class ApiProvider {

    /**
     * Creates a client instance of an API interface
     *
     * @param tClass  - interface describing an api
     * @param baseUrl - base url that will be used by client instance
     * @return instance of the API service client
     */
    public <T> T get(Class<T> tClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(tClass);
    }
}
