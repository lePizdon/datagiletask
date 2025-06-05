package com.datagile.test.weather.deserialized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRepresentation {
    @Getter
    @Setter
    @JsonProperty("location")
    private Location locationData;

    @Getter
    @Setter
    @JsonProperty("current")
    private Current currentData;

    @Override
    public String toString() {
        return currentData.toString() + locationData.toString();
    }
}
