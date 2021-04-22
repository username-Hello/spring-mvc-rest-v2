package guru.springfamework.springmvcrestv2.api.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VendorDTO {

    private String name;

    @JsonProperty("vendor_url")
    private String url;
}
