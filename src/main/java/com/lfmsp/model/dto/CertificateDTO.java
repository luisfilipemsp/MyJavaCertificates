package com.lfmsp.model.dto;

import java.util.Date;

public record CertificateDTO(
        String alias,
        String subject,
        String issuer,
        Date expiration,
        boolean valid
) {}