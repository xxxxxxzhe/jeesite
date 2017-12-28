/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.finbridge.web.manage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONPOJOBuilder;
import com.thinkgem.jeesite.common.utils.FastJsonUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cms.utils.HttpUtils;
import com.thinkgem.jeesite.modules.finbridge.contants.Constant;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbAssetInfo;
import com.thinkgem.jeesite.modules.finbridge.entity.manage.FbFundInfo;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbAssetInfoService;
import com.thinkgem.jeesite.modules.finbridge.service.manage.FbFundInfoService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * 后台操作相关接口 Controller
 * @author xz
 * @version 2017-12-12
 */
@Controller
@RequestMapping(value = "/operate")
public class OperateController extends BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(OperateController.class);

	@Autowired
	private FbFundInfoService fbFundInfoService;
	@Autowired
    private FbAssetInfoService fbAssetInfoService;

	/**
	 * 上传轮播图api
	 * @author xz
	 * @version 2017-12-15
	 */
	@RequestMapping(value = "uploadImage",method = RequestMethod.POST)
	@ResponseBody
	public String uploadImage(MultipartHttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Iterator<String> itr = request.getFileNames();

		while (itr.hasNext()) {
			MultipartFile file = request.getFile(itr.next());

			if (file != null && !file.equals("")) {
				try {

					String fileName = file.getOriginalFilename();
					//String imagePathName = "/banner/" + fileName;
                    String imagePathName = fileName;
					LOG.info("上传banner图：" + fileName);

					// 判断文件类型
					String type = fileName.contains(".")
							? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;

					// 判断文件类型是否为空
					if (type != null) {
						if ("JPEG".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
								|| "JPG".equals(type.toUpperCase())) {
							/*fileName = phone + "Icon." + type;*/
							byte[] bytes = file.getBytes();
							String path = Constant.BASE_DIR + Constant.USERFILES_BASE_URL + Constant.bannerPath;
							BufferedOutputStream outputStream =
									new BufferedOutputStream(new FileOutputStream(new File(path, imagePathName)));
							outputStream.write(bytes);
							outputStream.close();

						} else {
							LOG.error("上传轮播图失败,文件格式不符合");

							return FastJsonUtils.resultError(-100, "失败,文件格式不符合," + fileName, null);
						}

					} else {
						LOG.error("上传轮播图失败,文件格式为空");
						return FastJsonUtils.resultError(-100, "失败,文件格式为空," + fileName, null);
					}

				} catch (Exception e) {
					LOG.error("上传轮播图失败"+ e);
					return FastJsonUtils.resultError(-100, "失败," + file.getOriginalFilename(), null);
				}
			} else {
				return FastJsonUtils.resultError(-100, "失败，文件为空", null);
			}
		}

		return FastJsonUtils.resultSuccess(200, "ok", "上传成功");

	}

    /**
     * 上传文章缩略图api
     * @author xz
     * @version 2017-12-15
     */
    @RequestMapping(value = "uploadArticleImage",method = RequestMethod.POST)
    @ResponseBody
    public String uploadArticleImage(MultipartHttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Iterator<String> itr = request.getFileNames();

        while (itr.hasNext()) {
            MultipartFile file = request.getFile(itr.next());

            if (file != null && !file.equals("")) {
                try {

                    String fileName = file.getOriginalFilename();
                    //String imagePathName = "/banner/" + fileName;
                    String imagePathName = fileName;
                    LOG.info("上传文章缩略图：" + fileName);

                    // 判断文件类型
                    String type = fileName.contains(".")
                            ? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()) : null;

                    // 判断文件类型是否为空
                    if (type != null) {
                        if ("JPEG".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase())
                                || "JPG".equals(type.toUpperCase())) {
                            /*fileName = phone + "Icon." + type;*/
                            byte[] bytes = file.getBytes();
                            String path = Constant.BASE_DIR + Constant.USERFILES_BASE_URL + Constant.ARTICLE_PIC;
                            BufferedOutputStream outputStream =
                                    new BufferedOutputStream(new FileOutputStream(new File(path, imagePathName)));
                            outputStream.write(bytes);
                            outputStream.close();

                        } else {
                            LOG.error("上传文章缩略图失败,文件格式不符合");

                            return FastJsonUtils.resultError(-100, "失败,文件格式不符合," + fileName, null);
                        }

                    } else {
                        LOG.error("上传文章缩略图失败,文件格式为空");
                        return FastJsonUtils.resultError(-100, "失败,文件格式为空," + fileName, null);
                    }

                } catch (Exception e) {
                    LOG.error("上传文章缩略图失败"+ e);
                    return FastJsonUtils.resultError(-100, "失败," + file.getOriginalFilename(), null);
                }
            } else {
                return FastJsonUtils.resultError(-100, "失败，文件为空", null);
            }
        }

        return FastJsonUtils.resultSuccess(200, "ok", "上传成功");

    }


    /**
     * 获取轮播图api Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "banner/{pic}", method = RequestMethod.GET)
	public void getBanner(@PathVariable String pic, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {

			LOG.info("获取banner，" + pic);

			response.setDateHeader("Expires", 0);
			response.setHeader("Cache-Control",
					"no-store, no-cache, must-revalidate");
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			response.setHeader("Pragma", "no-cache");

			String filepath = request.getRequestURI();

            if(filepath.indexOf(".") != -1){

                String filetype = filepath.substring(filepath.indexOf("."));

                String path = "/banner/"+pic+filetype;
                if (path != null && !path.isEmpty()) {
                    response.setContentType((String) "image/" + path.substring(path.indexOf(".") + 1));
                    File file = new File(Constant.BASE_DIR + Constant.USERFILES_BASE_URL + path);
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
            }

			}else{
                LOG.info("banner ERROR,请检查图片名称");
            }
		} catch (Exception e) {
			LOG.error("获取banner图失败."+e);
		}
	}

    /**
     * 获取文章缩略图api Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "article/{pic}", method = RequestMethod.GET)
    public void getArticle(@PathVariable String pic, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {

            LOG.info("获取banner，" + pic);

            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control",
                    "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");

            String filepath = request.getRequestURI();

            if(filepath.indexOf(".") != -1){

                String filetype = filepath.substring(filepath.indexOf("."));

                String path = "/article/"+pic+filetype;
                if (path != null && !path.isEmpty()) {
                    response.setContentType((String) "image/" + path.substring(path.indexOf(".") + 1));
                    File file = new File(Constant.BASE_DIR + Constant.USERFILES_BASE_URL + path);
                    BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
                    FileCopyUtils.copy(inputStream, response.getOutputStream());
                }

            }else{
                LOG.info("article ERROR,请检查图片名称");
            }
        } catch (Exception e) {
            LOG.error("获取文章缩略图失败."+ e);
        }
    }


    /**
     * 资金审核拒绝 Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "fbFundRefuse")
    public @ResponseBody
    String fbFundRefuse(FbFundInfo fbFundInfo) {

        String id = fbFundInfo.getId();
        String aduitContentfb = fbFundInfo.getAduitContent();

        Integer num = null;
        try{
            if (id == null || aduitContentfb == null || "".equals(aduitContentfb)) {
                return FastJsonUtils.resultError(300, "获取必要参数错误", null);
            } else {

                fbFundInfo.setListStatus(3); //审核失败
                fbFundInfo.setId(id);//设置指定更改id
                fbFundInfo.setAduitStatus(3);//审批不通过
                fbFundInfo.setAduitContent(aduitContentfb);
                fbFundInfo.setAduitPerson(UserUtils.getUser().getLoginName());

                fbFundInfoService.save(fbFundInfo);

                //通知处理
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",fbFundInfo.getBelongTo());
                jsonObject.put("projectId",fbFundInfo.getFundId());
                jsonObject.put("aduitResult",0);

                notification(jsonObject);
            }
        }catch (Exception e){
            LOG.error("审核拒绝操作异常："+e);
            return FastJsonUtils.resultError(-100,"审批异常",null);
        }
        return FastJsonUtils.resultSuccess(200,"审批成功",null);
    }

    /**
     * 资金审核通过 Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "fbFundPass")
    public @ResponseBody
    String fbFundPass(FbFundInfo fbFundInfo) {

        String id = fbFundInfo.getId();
        String aduitContentfb = fbFundInfo.getAduitContent();

        Integer num = null;
        try{
            if (id == null || aduitContentfb == null || "".equals(aduitContentfb)) {
                return FastJsonUtils.resultError(300, "获取必要参数错误", null);
            } else {

                fbFundInfo.setListStatus(2); //挂牌中
                fbFundInfo.setId(id);//设置指定更改id
                fbFundInfo.setAduitStatus(2);//审批通过
                fbFundInfo.setListTime(new Date()); //设置挂牌时间
                fbFundInfo.setAduitContent(aduitContentfb);
                fbFundInfo.setAduitPerson(UserUtils.getUser().getLoginName());

                fbFundInfoService.save(fbFundInfo);


                //通知处理
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",fbFundInfo.getBelongTo());
                jsonObject.put("projectId",fbFundInfo.getFundId());
                jsonObject.put("aduitResult",1);

                notification(jsonObject);
            }
        }catch (Exception e){
            LOG.error("审核拒绝操作异常："+e);
            return FastJsonUtils.resultError(-100,"审批异常",null);
        }
        return FastJsonUtils.resultSuccess(200,"审批成功",null);
    }

    /**
     * 资产审核拒绝 Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "fbAssetRefuse")
    public @ResponseBody
    String fbAssetRefuse(FbAssetInfo fbAssetInfo) {

        String id = fbAssetInfo.getId();
        String aduitContentfb = fbAssetInfo.getAduitContent();

        Integer num = null;
        try{
            if (id == null || aduitContentfb == null || "".equals(aduitContentfb)) {
                return FastJsonUtils.resultError(300, "获取必要参数错误", null);
            } else {

                fbAssetInfo.setListStatus(3); //审核失败
                fbAssetInfo.setId(id);//设置指定更改id
                fbAssetInfo.setAduitStatus(3);//审批不通过
                fbAssetInfo.setAduitContent(aduitContentfb);
                fbAssetInfo.setAduitPerson(UserUtils.getUser().getLoginName());

                fbAssetInfoService.save(fbAssetInfo);

                //通知处理
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",fbAssetInfo.getBelongTo());
                jsonObject.put("projectId",fbAssetInfo.getAssetId());
                jsonObject.put("aduitResult",1);

                notification(jsonObject);
            }
        }catch (Exception e){
            LOG.error("审核拒绝操作异常："+e);
            return FastJsonUtils.resultError(-100,"审批异常",null);
        }
        return FastJsonUtils.resultSuccess(200,"审批成功",null);
    }

    /**
     * 资产审核通过 Controller
     * @author xz
     * @version 2017-12-12
     */
    @RequestMapping(value = "fbAssetPass")
    public @ResponseBody
    String fbAssetPass(FbAssetInfo fbAssetInfo) {

        String id = fbAssetInfo.getId();
        String aduitContentfb = fbAssetInfo.getAduitContent();

        Integer num = null;
        try{
            if (id == null || aduitContentfb == null || "".equals(aduitContentfb)) {
                return FastJsonUtils.resultError(300, "获取必要参数错误", null);
            } else {

                fbAssetInfo.setListStatus(2); //挂牌中
                fbAssetInfo.setId(id);//设置指定更改id
                fbAssetInfo.setAduitStatus(2);//审批通过
                fbAssetInfo.setListTime(new Date()); //设置挂牌时间
                fbAssetInfo.setAduitContent(aduitContentfb);
                fbAssetInfo.setAduitPerson(UserUtils.getUser().getLoginName());

                fbAssetInfoService.save(fbAssetInfo);

                //通知处理
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("userId",fbAssetInfo.getBelongTo());
                jsonObject.put("projectId",fbAssetInfo.getAssetId());
                jsonObject.put("aduitResult",1);

                notification(jsonObject);
            }
        }catch (Exception e){
            LOG.error("审核拒绝操作异常："+e);
            return FastJsonUtils.resultError(-100,"审批异常",null);
        }
        return FastJsonUtils.resultSuccess(200,"审批成功",null);
    }

    /**
     * 处理结果通知方法
     * userId projectId aduitResult
     * post
     * @author xz
     * @version 2017-12-12
     */

    public String notification(JSONObject jsonObject){

        String rs = null;

        try {

            if(!jsonObject.containsKey("userId")||!jsonObject.containsKey("projectId") || !jsonObject.containsKey("aduitResult")){

                return FastJsonUtils.resultError(-100,"参数不完整",null);

            }

            rs = HttpUtils.sendPostJson(Constant.aduitResuleNotifyUrl, jsonObject);

            LOG.info("请求参数：" + jsonObject.toJSONString());

            LOG.info("通知结果：" + rs);

            JSONObject rs_code = JSON.parseObject(rs);

            if ("200".equals(rs_code.getString("code"))) {
                return FastJsonUtils.resultSuccess(200,"通知成功",rs);
            }else{
                return FastJsonUtils.resultError(-100,"通知失败",rs);
            }
        } catch (Exception e) {
            LOG.error("login ERROR"+ e);
            return FastJsonUtils.resultError(-100,"异常",null);
        }

    }


}