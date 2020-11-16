package com.sda.weather.localisation;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class Localisation {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
//    @NonNull
    private String cityName;
    private String latitude;
    private String longitude;
//    @NonNull
    private String countryName;
    private String region;

}
