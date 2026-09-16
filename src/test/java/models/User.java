package models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int id;
    private String email;

    @JsonProperty("first_name")
    private String firstName;
    private String lastName;
    private String createdAt;
    private String updatedAt;
}
