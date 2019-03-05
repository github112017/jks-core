package com.liumapp.jks.core.signature;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.awt.AsianFontMapper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import com.liumapp.jks.core.filter.RequestFilter;
import com.liumapp.jks.core.signature.require.Base64StringSignerRequire;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Base64StringSigner extends RequestFilter<Base64StringSignerRequire> {

    @Override
    public JSONObject handle(Base64StringSignerRequire data) {
        try {
            PdfReader pdfReader = new PdfReader(data.getPdfPath());
            PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(data.getResultPath()));
            PdfContentByte canvas = stamper.getOverContent(data.getPage());
            BaseFont baseFont = BaseFont.createFont(AsianFontMapper.ChineseSimplifiedFont,  AsianFontMapper.ChineseSimplifiedEncoding_H, BaseFont.NOT_EMBEDDED);
            Font font = new Font(baseFont, 8);
            ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, new Phrase(data.getContent(), font),
                    data.getFirstX(), data.getFirstY(), 0);
            stamper.close();
            this.jobResult.put("result", "success");
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}