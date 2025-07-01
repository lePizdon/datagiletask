package com.datagile.test.weather.deserialized;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class Current {


    @JsonProperty("temp_c")
    private Double temperature;


    private Condition condition;


    @JsonProperty("feelslike_c")
    private Double feelslike;


    @JsonProperty("humidity")
    private Double humidity;


    @JsonProperty("wind_mph")
    private Double wind;


    @JsonProperty("pressure_mb")
    private Double pressure;

    @Override
    public String toString() {
        return "t: " + temperature.toString() + "\nfeelslike t: " + feelslike.toString() + "\nhumidity: "
                + humidity.toString() + "\npressure: " + pressure.toString() + "\nwind: " + wind.toString()
                + "\ncondition: " + condition.toString();
    }
}
