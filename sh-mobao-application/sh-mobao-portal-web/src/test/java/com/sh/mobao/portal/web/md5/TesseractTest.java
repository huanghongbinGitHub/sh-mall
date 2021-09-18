package com.sh.mobao.portal.web.md5;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class TesseractTest {

    public static void main(String[] args) throws TesseractException {

        ITesseract iTesseract = new Tesseract();
        //语言包 加进来
        iTesseract.setDatapath("/Users/xiaohuang/sh/tessdata");
        iTesseract.setLanguage("chi_sim");

        File file = new File("/Users/xiaohuang/sh/img/tes.jpeg");
        String s = iTesseract.doOCR(file);
        System.out.println(s);
    }



}
