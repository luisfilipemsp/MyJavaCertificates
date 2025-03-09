package com.lfmsp;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.security.cert.X509Certificate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String rutaCacerts = System.getProperty("java.home") + "/lib/security/cacerts";
        String password = "changeit";

        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance("JKS");
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
        try (FileInputStream fis = new FileInputStream(rutaCacerts)) {
            keyStore.load(fis, password.toCharArray());
        } catch (NoSuchAlgorithmException | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }

        // Listar los certificados
        Enumeration<String> aliases = null;
        try {
            aliases = keyStore.aliases();
        } catch (KeyStoreException e) {
            throw new RuntimeException(e);
        }
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            Certificate cert = null;
            try {
                cert = keyStore.getCertificate(alias);
            } catch (KeyStoreException e) {
                throw new RuntimeException(e);
            }
            if (cert instanceof X509Certificate x509Cert) {
                Date fechaExpiracion = x509Cert.getNotAfter();
                System.out.println("Alias: " + alias);
                System.out.println("Fecha de Expiración: " + formatoFecha.format(fechaExpiracion));
                System.out.println(); // Espacio entre certificados
            }
        }
    }
}