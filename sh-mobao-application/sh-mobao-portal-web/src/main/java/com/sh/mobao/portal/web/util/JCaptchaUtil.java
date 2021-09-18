package com.sh.mobao.portal.web.util;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomListColorGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

import java.awt.*;

public class JCaptchaUtil {

    private static final ImageCaptchaService service = imageCaptchaService();

    public static ImageCaptchaService getService(){
        return service;
    }


    public static ImageCaptchaService imageCaptchaService() {
        //背景
        UniColorBackgroundGenerator backgroundGenerator = new UniColorBackgroundGenerator(100,100);

        //字
        RandomRangeColorGenerator colorGenerator = new RandomRangeColorGenerator(new int[]{0,100},new int[]{0,200},new int[]{50,250});
        RandomTextPaster randomTextPaster = new RandomTextPaster(4,5,colorGenerator);
        //字体
        RandomFontGenerator fontGenerator = new RandomFontGenerator(20,30,new Font[]{new Font("Courier",Font.PLAIN,20)});

        //组装图像
        ComposedWordToImage wordToImage = new ComposedWordToImage(fontGenerator,backgroundGenerator,randomTextPaster);

        ComposeDictionaryWordGenerator wordGenerator = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));

        GimpyFactory factory = new GimpyFactory(wordGenerator,wordToImage);

        GenericCaptchaEngine engine = new GenericCaptchaEngine(new CaptchaFactory[]{factory});


        return new GenericManageableCaptchaService(engine,20,2000,2000);
    }


}
