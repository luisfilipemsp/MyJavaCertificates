package com.lfmsp.service;


import com.lfmsp.config.TrustStoreConfig;
import com.lfmsp.exception.CertificateAccessException;
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
        List<CertificateDTO> certificates = new ArrayList<>();
        KeyStore trustStore = null;
        FileInputStream fis = null;

        try {
            trustStore = KeyStore.getInstance("JKS");
            fis = new FileInputStream(config.getPath());
            trustStore.load(fis, config.getPassword().toCharArray());

            Enumeration<String> aliases = trustStore.aliases();
            Date currentDate = new Date();

            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                Certificate cert = trustStore.getCertificate(alias);

                if (cert instanceof X509Certificate) {
                    X509Certificate x509Cert = (X509Certificate) cert;

                    certificates.add(new CertificateDTO(
                            alias,
                            x509Cert.getSubjectX500Principal().getName(),
                            x509Cert.getIssuerX500Principal().getName(),
                            x509Cert.getNotAfter(),
                            currentDate.before(x509Cert.getNotAfter())
                    ));
                }
            }

            return certificates;

        } catch (KeyStoreException e) {
            throw new CertificateAccessException("Error en la configuración del almacén", e);
        } catch (NoSuchAlgorithmException e) {
            throw new CertificateAccessException("Algoritmo no soportado", e);
        } catch (CertificateException e) {
            throw new CertificateAccessException("Certificado inválido", e);
        } catch (IOException e) {
            throw new CertificateAccessException("Error de E/S accediendo al almacén", e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                   // logger.error("Error cerrando el flujo del almacén", e);
                }
            }
        }
    }

    public CertificateDetailDTO getCertificateDetails(String alias) {
        CertificateDetailDTO result = null;

        return result;
    }

}