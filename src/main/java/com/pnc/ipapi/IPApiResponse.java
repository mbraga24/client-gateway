package com.pnc.ipapi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IPApiResponse {

    private String country;
    private String countryCode;
    private String regionName;

}
