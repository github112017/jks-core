package com.liumapp.jks.core;

import com.alibaba.fastjson.JSONObject;
import com.liumapp.jks.core.certificate.ExportCertificate;
import com.liumapp.jks.core.certificate.GenerateCertificate;
import com.liumapp.jks.core.certificate.require.ExportCertificateRequire;
import com.liumapp.jks.core.certificate.require.GenerateCertificateRequire;
import com.liumapp.jks.core.container.GenerateJksContainer;
import com.liumapp.jks.core.container.require.GenerateJksContainerRequire;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;

/**
 * @author liumapp
 * @file JksCoreTest.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/28/18
 */
public class JksCoreTest extends TestCase {

    private String jksSavePath = "/usr/local/tomcat/project/jks-core";

    @Override
    protected void setUp() throws Exception {
        File file = new File(this.jksSavePath);
        Assert.assertEquals(true, file.isDirectory());
        super.setUp();
    }

    /**
     * you can use
     * keytool -list -v -keystore ./demo.ks
     * to view keystore detail
     */
    @Test
    public void testGenerateContainer () {
        JksCore jksCore = new JksCore();
        GenerateJksContainer generateJksContainer = new GenerateJksContainer();
        GenerateJksContainerRequire generateJksContainerRequire = new GenerateJksContainerRequire();
        generateJksContainerRequire.setSavePath(this.jksSavePath);//container save path
        generateJksContainerRequire.setKeyStoreName("demo.ks");
        generateJksContainerRequire.setKeyStorePd("123456");//container password
        generateJksContainerRequire.setFcAlias("first-cert");//alias of first certificate
        generateJksContainerRequire.setFcPassword("123123");//password of first certificate
        generateJksContainerRequire.setFcName("liumapp");
        generateJksContainerRequire.setFcCountry("CN");
        generateJksContainerRequire.setFcProvince("ZJ");
        generateJksContainerRequire.setFcCity("Hangzhou");
        JSONObject result = jksCore.doJob(generateJksContainer, generateJksContainerRequire);
        Assert.assertEquals("success", result.get("msg"));
    }

    @Test
    public void testGenerateCertificate () {
        JksCore jksCore = new JksCore();
        GenerateCertificate generateCertificate = new GenerateCertificate();
        GenerateCertificateRequire generateCertificateRequire = new GenerateCertificateRequire();
        generateCertificateRequire.setAlias("second-cert");
        generateCertificateRequire.setCertPassword("123123");
        generateCertificateRequire.setCountry("CN");
        generateCertificateRequire.setProvince("ZJ");
        generateCertificateRequire.setCity("Hangzhou");
        generateCertificateRequire.setKeystorePath(this.jksSavePath);
        generateCertificateRequire.setKeystoreName("demo.ks");
        generateCertificateRequire.setStorepass("123456");
        generateCertificateRequire.setValidity(1);
        generateCertificateRequire.setName("zhangsan");
        JSONObject result = jksCore.doJob(generateCertificate, generateCertificateRequire);
        Assert.assertEquals("success", result.get("msg"));
    }

    @Test
    public void testExportCertificate () {
        JksCore jksCore = new JksCore();
        ExportCertificate exportCertificate = new ExportCertificate();
        ExportCertificateRequire exportCertificateRequire = new ExportCertificateRequire();
        JSONObject result = jksCore.doJob(exportCertificate, exportCertificateRequire);
    }

    @Test
    public void testSignCertificateToPdf () {
        JksCore jksCore = new JksCore();

    }

}
