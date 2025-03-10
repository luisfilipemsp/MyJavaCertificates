package com.lfmsp.controller;

import com.lfmsp.model.dto.CertificateDTO;
import com.lfmsp.model.dto.CertificateDetailDTO;
import com.lfmsp.service.CertificateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    public List<CertificateDTO> getAllCertificates() {
        return certificateService.getAllCertificates();
    }

    @GetMapping("/{alias}")
    public CertificateDetailDTO getCertificateDetails(@PathVariable String alias) {
        return certificateService.getCertificateDetails(alias);
    }
}
