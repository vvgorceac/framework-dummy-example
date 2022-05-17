package md.vgorceac.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Photo {
    @JsonProperty("albumId")
    private Integer albumId;

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("thumbnailUrl")
    private String thumbnailUrl;
}