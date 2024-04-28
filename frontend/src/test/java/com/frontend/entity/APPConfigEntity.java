package com.frontend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class APPConfigEntity {
    @JsonProperty("HTTPClient")
    private HttpClient httpClient;
    @Data
    static public class HttpClient{
        private  String host;


    }
}


