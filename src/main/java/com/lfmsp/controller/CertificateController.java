package com.lfmsp.controller;

import com.lfmsp.model.dto.CertificateDTO;
import com.lfmsp.model.dto.CertificateDetailDTO;
import com.lfmsp.service.CertificateService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CertificateDTO>> getAllCertificates() {
        return ResponseEntity.ok()
                .header("Cache-Control", "no-cache, must-revalidate")
                .body(certificateService.getAllCertificates());
    }

    @GetMapping("/{alias}")
    public ResponseEntity<CertificateDetailDTO> getCertificateDetails(@PathVariable String alias) {
        return ResponseEntity.ok()
                .header("Cache-Control", "no-cache, must-revalidate")
                .body(certificateService.getCertificateDetails(alias));
    }
}
