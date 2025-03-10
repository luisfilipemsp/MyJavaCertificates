package com.lfmsp.model.dto;

import java.util.Date;

public record CertificateDetailDTO(
        String alias,
        String subject,
        String issuer,
        String serialNumber,
        Date validFrom,
        Date validTo,
        long remainingDays,
        String signatureAlgorithm,
        boolean valid
) {}
