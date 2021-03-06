package com.sh.mobao.portal.web.util;

import cn.hutool.core.collection.CollectionUtil;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author 马士兵教育:chaopengfei
 */
public class SliderUtil {

	public static VerificationVO  cut(){
		// 读取图库目录
		File imgCatalog = new File("/Users/xiaohuang/sh/project/sh-mall/slider/targets");	//放背景图的目录
		ArrayList<File> files = CollectionUtil.newArrayList(imgCatalog.listFiles());
		if (CollectionUtil.isNotEmpty(files)) {
			// 随机选择需要切的图
			int randNum = new Random().nextInt(files.size());
			File targetFile = files.get(randNum);

			// 随机选择剪切模版，小的模板
			File tempImgFile = new File("/Users/xiaohuang/sh/project/sh-mall/slider/templates/" + (new Random().nextInt(2) + 1) + "-w.png");
			try {
				VerificationVO verificationVO = VerifyImageUtils.pictureTemplatesCut(tempImgFile, targetFile);
				System.out.println("宽："+verificationVO.getXWidth());
//				redisUtils.set(AuthConstant.PREFIX_AUTH_VC_CODE_CACHE + account, verificationVO.getXWidth(), 1800);

//				// 移除横坐标送前端
//				verificationVO.setXWidth(null);

				return verificationVO;
			}catch (Exception e) {
				System.out.println("切图失败");
			}
		}

		return null;
	}


}