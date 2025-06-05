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
public class Current {
    @Getter
    @Setter
    @JsonProperty("temp_c")
    private Double temperature;

    @Getter
    @Setter
    private Condition condition;

    @Getter
    @Setter
    @JsonProperty("feelslike_c")
    private Double feelslike;

    @Getter
    @Setter
    @JsonProperty("humidity")
    private Double humidity;

    @Getter
    @Setter
    @JsonProperty("wind_mph")
    private Double wind;

    @Getter
    @Setter
    @JsonProperty("pressure_mb")
    private Double pressure;

    @Override
    public String toString() {
        return "t: " + temperature.toString() + "\nfeelslike t: " + feelslike.toString() + "\nhumidity: "
                + humidity.toString() + "\npressure: " + pressure.toString() + "\nwind: " + wind.toString()
                + "\ncondition: " + condition.toString() ;
    }
}
