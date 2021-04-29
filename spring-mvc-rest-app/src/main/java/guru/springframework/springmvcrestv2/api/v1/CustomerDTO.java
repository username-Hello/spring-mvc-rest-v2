package guru.springframework.springmvcrestv2.api.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CustomerDTO {

    private String customer_url;

    @ApiModelProperty(value = "just first name", required = true)
    @JsonProperty("firstname")
    private String firstName;

    @ApiModelProperty(value = "just last name", required = true)
    @JsonProperty("lastname")
    private String lastName;
}
