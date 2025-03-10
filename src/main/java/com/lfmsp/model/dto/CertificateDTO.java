package com.lfmsp.model.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

public record CertificateDTO(
        @JsonProperty("alias")
        String alias,

        @JsonProperty("sujeto")
        String subject,

        @JsonProperty("emisor")
        String issuer,

        @JsonProperty("expiracion")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
        Date expiration,

        @JsonProperty("valido")
        boolean valid
) {}