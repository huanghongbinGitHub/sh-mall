package com.sh.mobao.portal.web.code;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import lombok.Data;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Random;

@Data
public class ImageCode {

    //图形中的内容
    private String code;

    //图片
    private ByteArrayInputStream image;

    private int wigdth = 400;

    private int height = 100;

    //单例
    public static ImageCode getInstance() throws Exception {
        return new ImageCode();
    }


    private ImageCode() throws Exception{
        //图形缓冲区-记忆
        BufferedImage image = new BufferedImage(wigdth,height,BufferedImage.TYPE_INT_RGB);

        //画笔
        Graphics graphics = image.getGraphics();

        //涂色，画图
        graphics.setColor(new Color(17,147,37));
        //画矩形
        graphics.fillRect(0,0,wigdth,height);

        //第一种方式画验证码
//        this.drawVericode(graphics);

        //画加法
        this.drawDisturbLine(graphics);

        //画100条线
        this.drawAdditionACode(graphics);


        //收笔
        graphics.dispose();

        ByteArrayInputStream inputStream = null;
        ByteOutputStream outputStream = new ByteOutputStream();

        try {
            //赋值给byteArrayInputStream
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(outputStream);
            ImageIO.write(image, "jpeg", imageOutputStream);

            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        }catch (Exception e){
            System.out.println("生成验证码码失败");
        }
        this.image = inputStream;

    }

    /**
     * 画加法验证码
     * @param graphics
     */
    public void drawAdditionACode(Graphics graphics){
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        Random random = new Random();
        int numb1 = random.nextInt(20);
        int numb2 = random.nextInt(20);
        graphics.setColor(Color.black);
        graphics.drawString(numb1 +"",wigdth / 6 ,60);
        graphics.drawString("+",wigdth / 6 * 2,60);
        graphics.drawString(numb2 + "",wigdth / 6 * 3,60);
        graphics.drawString("=",wigdth / 6 * 4,60);
        graphics.drawString("?",wigdth / 6 * 5,60);
        this.code = (numb1 + numb2) +"";

    }

    /**
     * 画六位数字验证码
     * @param graphics
     */
    public void drawVericode(Graphics graphics){
        //字体,生成刘伟数字
        graphics.setFont(new Font("宋体",Font.PLAIN,30));
        Random random = new Random();
        code = "";
        for (int i = 0; i < 6; i++){
            String s = String.valueOf(random.nextInt(10));
            this.code += s;
//            graphics.setColor(new Color(218,61,37));
            graphics.setColor(Color.orange);
            graphics.drawString(s,(wigdth / 6) * i + 20,60);
//            graphics.drawLine((wigdth / 6) * i,40,(wigdth / 6) * i + 25,40 - 10);
        }
    }

    /**
     * 画100条线
     * @param graphics
     */
    public void drawDisturbLine(Graphics graphics){
        Random random = new Random();
        //画100条线条
        graphics.setColor(Color.blue);
        for (int j = 0; j < 100; j++){
            int x = random.nextInt(wigdth);
            int y = random.nextInt(height);
            int x1 = random.nextInt(20);
            int y2 = random.nextInt(20);
            graphics.drawLine(x,y,x + x1,y + y2);
        }
    }


}
