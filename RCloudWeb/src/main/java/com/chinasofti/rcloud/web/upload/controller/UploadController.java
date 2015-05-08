package com.chinasofti.rcloud.web.upload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.chinasofti.rcloud.web.common.BasicController;


@Controller
public class UploadController extends BasicController{
	
	
	@RequestMapping("uploadController/index")
	public String index(){
		return "test/add";
	}
	
	/**
	 * 上传
	 * @param myfiles
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="uploadController/upload", method=RequestMethod.POST)  
    public String addUser(@RequestParam MultipartFile[] myfiles, HttpServletRequest request,Model model) throws IOException{
		List<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
		
        for(MultipartFile myfile : myfiles){  
        	
            if(myfile.isEmpty()){  
                System.out.println("文件未上传");  
            }else{  
                System.out.println("文件长度: " + myfile.getSize());  
                System.out.println("文件类型: " + myfile.getContentType());  
                System.out.println("文件名称: " + myfile.getName());  
                System.out.println("文件原名: " + myfile.getOriginalFilename());  
                System.out.println("========================================");  
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload");
                
                HashMap<String, String> map=new HashMap<String, String>();
                map.put("name", myfile.getOriginalFilename());
                list.add(map);
                FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, myfile.getOriginalFilename()));  
            }  
        }  
        model.addAttribute("list",list);
        return "test/list";
    }  
	
	
	/**
	 * 下载
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("uploadController/download/{fileName}") 
	@ResponseBody
    public ModelAndView download(@PathVariable("fileName")  
    String fileName, HttpServletRequest request, HttpServletResponse response)  
            throws Exception {  
  
        response.setContentType("text/html;charset=utf-8");  
        request.setCharacterEncoding("UTF-8");  
        java.io.BufferedInputStream bis = null;  
        java.io.BufferedOutputStream bos = null;  
  
        String ctxPath = request.getSession().getServletContext().getRealPath(  
                "/WEB-INF/upload")  
                + File.separator + fileName;  
        String downLoadPath = ctxPath ;  
        System.out.println(downLoadPath);  
        try {  
            long fileLength = new File(downLoadPath).length();  
            response.setContentType("application/x-msdownload;");  
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(fileName.getBytes("utf-8"), "ISO8859-1"));  
            response.setHeader("Content-Length", String.valueOf(fileLength));  
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));  
            bos = new BufferedOutputStream(response.getOutputStream());  
            byte[] buff = new byte[2048];  
            int bytesRead;  
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
                bos.write(buff, 0, bytesRead);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            if (bis != null)  
                bis.close();  
            if (bos != null)  
                bos.close();  
        }  
        return null;  
    }  
	

}
