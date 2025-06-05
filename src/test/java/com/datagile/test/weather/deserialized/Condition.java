package com.datagile.test.weather.deserialized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    @Getter
    @Setter
    private String text;

    @Override
    public String toString() {
        return text;
    }
}
