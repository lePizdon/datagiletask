package com.datagile.test.weather.deserialized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Getter
    @Setter
    @JsonProperty("localtime")
    private String localtime;

    @Override
    public String toString() {
        return "\ndate: "+ localtime;
    }
}
