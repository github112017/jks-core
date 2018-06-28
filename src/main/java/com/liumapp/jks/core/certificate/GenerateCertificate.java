package com.liumapp.jks.core.certificate;

import com.alibaba.fastjson.JSONObject;
import com.liumapp.jks.core.certificate.require.GenerateCertificateRequire;
import com.liumapp.jks.core.filter.RequestFilter;
import com.liumapp.jks.core.loader.Resource;

/**
 * @author liumapp
 * @file GenerateCertificate.java
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 6/28/18
 */
public class GenerateCertificate extends RequestFilter<GenerateCertificateRequire> {

    @Override
    public JSONObject handle(GenerateCertificateRequire data) {
        Resource resource = Resource.from(data.getKeystorePath());

        return null;
    }

}
