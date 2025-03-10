package com.lfmsp.service;


import com.lfmsp.config.TrustStoreConfig;
import com.lfmsp.model.dto.CertificateDTO;
import com.lfmsp.model.dto.CertificateDetailDTO;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.text.SimpleDateFormat;

@Service
public class CertificateService {

    private final TrustStoreConfig config;

    public CertificateService(TrustStoreConfig config) {
        this.config = config;
    }

    public List<CertificateDTO> getAllCertificates() {
        List<CertificateDTO> result = new ArrayList<CertificateDTO>();

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }

        try (FileInputStream fis = new FileInputStream(config.getPath())) {
            keyStore.load(fis, config.getPassword().toCharArray());
        } catch (IOException |
                 NoSuchAlgorithmException | CertificateException e) {
            throw new RuntimeException(e);
        }

        Enumeration<String> aliases = null;
        try {
            aliases = keyStore.aliases();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            Certificate cert = null;
            try {
                cert = keyStore.getCertificate(alias);
            } catch (KeyStoreException e) {
                throw new RuntimeException(e);
            }
            if (cert instanceof X509Certificate x509Cert) {
                Date expiration = x509Cert.getNotAfter();
                /*System.out.println("Alias: " + alias);
                System.out.println("Fecha de Expiración: " + dateFormat.format(expiration));
                System.out.println(); // Espacio entre certificados*/
            }
        }


        return result;
    }

    public CertificateDetailDTO getCertificateDetails(String alias) {
        CertificateDetailDTO result = null;

        return result;
    }

}