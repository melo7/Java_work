package com.ssm.controller;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ssm.common.Result;
import com.ssm.common.SessionAttrs;
import com.ssm.model.AdderssAdmin;
import com.ssm.model.Advertising;
import com.ssm.model.ServicePackage;
import com.ssm.model.error.ApplicationAccessException;
import com.ssm.model.error.HtmlErrorPageException;
import com.ssm.service.AdvertisingService;

@Controller
@RequestMapping("/uploadPhoto")
public class PhotoController extends BaseController{
	
	@Resource
	private AdvertisingService advertisingService;
	
	@RequestMapping(value ="savePhoto")
	@ResponseBody
	public Result savePhoto (HttpServletRequest request,HttpSession session,
			@RequestParam MultipartFile multipartFile) throws HtmlErrorPageException{
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "0");
			BufferedImage image = ImageIO.read(multipartFile.getInputStream());//
			byte[] data = multipartFile.getBytes();//图片转换成二进制
//			Advertising advertising = new Advertising();
//			advertising.setPhotoName("picture");
//			advertising.setCreateBy("melon");
//			advertising.setCreateTime(new SimpleDateFormat("yyyy-MM-hh").format(new Date()));
//			advertising.setPhotoByte(data);
//			advertisingService.insert(advertising);
			if (data != null) {
				session.setAttribute(SessionAttrs.Advertising.PHOTO.getKey(), new Base64().encodeToString(data));
			}
			//--------------------------------------------二进制转换成file文件
//			OutputStream output = new FileOutputStream("");

//			BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);

//			bufferedOutput.write(data);
			System.out.println(image.getWidth());//图片宽度，单位px
			System.out.println(image.getHeight());//图片高度，单位px
			return Result.ok(map);
		} catch (Exception e) {
			throw new HtmlErrorPageException(e);
		}
	}
	
	/**
	 * 刷新列表
	 * @param model
	 * @return
	 * @throws ApplicationAccessException 
	 */
	@RequestMapping(value="showAdvertisingInfo")
	@ResponseBody
	public Map<String, Object> showAdvertisingInfo(Model model) throws ApplicationAccessException{
		
		try {
			
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code","0");
			List<Advertising> serList = advertisingService.queryAllAdvertising(); 
//			for (Advertising advertising : serList) {
//				Image image = BytesToImage(advertising.getPhotoByte());
//				advertising.setPhotoByte(image);
//			}
			reMap.put("length",serList.size());
			reMap.put("advertisingList", serList);
			return reMap;
		} catch (Exception e) {
			throw new ApplicationAccessException(e);
		}
	}
	
	private Image BytesToImage(byte[] photoByte) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 存入照片信息
	 * @throws ApplicationAccessException 
	 */
	@RequestMapping(value ="savePhotoInfo")
	@ResponseBody
	public Result savePhotoInfo(HttpSession session,
			@RequestParam (value = "imgName") String imgName,
			@RequestParam (value = "imgheight") String imgheight,
			@RequestParam (value = "imgweight") String imgweight,
			@RequestParam (value = "sort") String  title,
			@RequestParam (value = "content") String  content) throws ApplicationAccessException{
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", "1");
			if (StringUtils.isNotBlank(imgName) && StringUtils.isNotBlank(imgheight) && StringUtils.isNotBlank(imgweight) 
					&& StringUtils.isNotBlank(title) && StringUtils.isNotBlank(content)) {
				
				String data = SessionAttrs.Advertising.PHOTO.getVal(session,new String());
				byte[] imgData = data.getBytes();
				Advertising advertising = new Advertising();
				advertising.setPhotoName(imgName);
				advertising.setPhotoSize(imgweight+"*"+imgheight);
				advertising.setTitle(title);
				advertising.setPhotoByte(imgData);
				advertising.setCreateTime(new SimpleDateFormat("yyyy-MM-hh").format(new Date()));
				advertising.setPhotoContent(content);
				advertising.setCreateBy("melon");
				advertisingService.insert(advertising);
				map.put("code", "0");
			}
			
			return Result.ok();
		} catch (Exception e) {
			throw new ApplicationAccessException(e);
		}
	}
	
	
	@RequestMapping(value="deleteAdvertisingInfo")
	@ResponseBody
	public Map<String, Object> deleteAdvertisingInfo(Model model,
			@RequestParam (value = "id") String id) throws ApplicationAccessException{
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (StringUtils.isNotBlank(id)) {
				advertisingService.deleteAdvertising(id);
				reMap.put("code", "0");
			}
			return reMap;
		} catch (Exception e) {
			throw  new ApplicationAccessException(e);
		}
	}
	
	/**
	 * 编辑更改信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "updateAdvertisingInfo")
	@ResponseBody
	public Map<String, Object> updateAdvertisingInfo(Model model,
			@RequestParam (value = "id") String id){
		try {
			Map<String , Object> reMap = new HashMap<String, Object>();
			reMap.put("code", "1");
			if (id != null) {
				Advertising advertisingList = advertisingService.updateAdvertising(id);
				if (advertisingList != null) {
					reMap.put("advertisingList", advertisingList);
					reMap.put("code", "0");
				}
			}
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
	}
	
	/**
	 * 服务信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping (value = "saveAdvertisingInfo")
	@ResponseBody
	public Map<String, Object> saveAdvertisingInfo(Model model,
			@RequestParam (value = "id") int id,
			@RequestParam (value = "imgName") String imgName,
			@RequestParam (value = "imgheight") String imgheight,
			@RequestParam (value = "imgweight") String imgweight,
			@RequestParam (value = "sort") String  title,
			@RequestParam (value = "content") String content){
		
		try {
			Map<String, Object> reMap = new HashMap<String, Object>();
			Advertising advertising = new Advertising();
			advertising.setPhotoName(imgName);
			advertising.setPhotoSize(imgweight+"*"+imgheight);
			advertising.setTitle(title);
			advertising.setPhotoContent(content);
			advertising.setCreateBy("melon");
			advertisingService.saveAdvertising(advertising);
			reMap.put("code", "0");
			reMap.put("message", "修改信息成功！");
			return reMap;
		} catch (Exception e) {
			throw new MyException();
		}
		
	}
	
	/**
	 * 图片文件转换为二进制存数据库
	 * @param file
	 * @return
	 */
	public byte[] getBytes(File file){  
        
        byte[] buffer = null;
        if (file == null){
            return buffer;
        } else {             
            try {     
                FileInputStream fis = new FileInputStream(file);  
                ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
                byte[] b = new byte[1000];  
                int n;  
                while ((n = fis.read(b)) != -1) {  
                    bos.write(b, 0, n);  
                }  
                fis.close();  
                bos.close();  
                buffer = bos.toByteArray();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }
        }
         return buffer;  
	}
	
	/**
	 * 
	 * @param buf
	 * @param filePath
	 * @param fileName
	 */
	
	
}
