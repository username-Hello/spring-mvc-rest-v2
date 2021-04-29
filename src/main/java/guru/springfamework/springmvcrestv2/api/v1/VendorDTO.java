package guru.springfamework.springmvcrestv2.api.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {

    @ApiModelProperty(value = "Name of vendor", example = "Candy Daddy", required = true)
    private String name;

    @JsonProperty("vendor_url")
    @ApiModelProperty(example = "/api/v1/vendors/1")
    private String url;
}
