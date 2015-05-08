package com.chinasofti.rcloud.web.upload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chinasofti.rcloud.common.CommonUtil;
import com.chinasofti.rcloud.domain.ApplicationEntity;
import com.chinasofti.rcloud.portal.cloudshop.service.ApplicationPublishService;
import com.chinasofti.rcloud.web.common.BasicController;
import com.chinasofti.rcloud.web.common.FileUtil;
import com.chinasofti.rcloud.web.common.LoginUtil;

@Controller
@RequestMapping("/uploadVersion")
public class UploadVersionController extends BasicController {

	public static Logger logger = Logger
			.getLogger(UploadVersionController.class);

	@Autowired
	ApplicationPublishService applicationPublishService;

	@RequestMapping("/index")
	public String index() {
		return "test/addVersion";
	}

	/**
	 * 上传
	 * 
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
//	@ResponseBody
	public void uploadVersion(
			@RequestParam MultipartFile[] file, HttpServletRequest request,HttpServletResponse response,
			Model model) throws IOException {
//		ResponseEntity<String> responseEntity = new ResponseEntity<String>();
		for (MultipartFile myfile : file) {
			if (myfile.isEmpty()) {
				logger.info("文件未上传");
			} else {
				logger.info("文件长度: " + myfile.getSize());
				logger.info("文件类型: " + myfile.getContentType());
				logger.info("文件名称: " + myfile.getName());
				logger.info("文件原名: " + myfile.getOriginalFilename());
				logger.info("工程路径/: "
						+ request.getSession().getServletContext()
								.getRealPath("/"));
				String realPath = request.getSession().getServletContext()
						.getRealPath("")
						+ File.separator
						+ FileUtil.TMP_PATH
						+ File.separator
						+ LoginUtil.getUserId();
				logger.info("文件路径" + realPath);
				logger.info("========================================");
				FileUtil.deleteFloderFile(realPath); // 删除用户目录下的文件
				FileUtils.copyInputStreamToFile(myfile.getInputStream(),
						new File(realPath, myfile.getOriginalFilename()));
//				responseEntity.setEntity("success");
			}
		}
		
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("{'entity':'success','status':200,'errorMessage':''}");
		//方法示例..详细方法可以继续补充
		out.flush();
		out.close();
		
//		return responseEntity;
	}

	/**
	 * 下载
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("download/{applicationId}/")
	public String download(@PathVariable("applicationId") String applicationId,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		ApplicationEntity applicationEntity = applicationPublishService
				.getApplicationPublishInfo(applicationId);
		String downLoadPath = FileUtil.getDestFilePath(
				applicationEntity.getProviderId(), applicationId);
		File destfile = new File(downLoadPath);
		File downFile = null;
		if (destfile.isDirectory()) {
			File[] files = destfile.listFiles();
			for (File file : files) {
				downFile = file;
			}
		}
		String fileName = downFile.getName();
		int filetynwe = fileName.lastIndexOf(".");
		String baseApp = fileName.substring(filetynwe);

		try {
			response.setContentType("application/x-msdownload;");
			// response.setHeader("Content-disposition", "attachment; filename="
			// + new String(fileName.getBytes("utf-8"), "ISO8859-1"));

			StringBuffer contentDisposition = new StringBuffer(
					"attachment; filename=");
			if (baseApp != null) {
				contentDisposition
						.append(CommonUtil.dateToString(new Date(),
								"yyyy-MM-dd_HH:mm:ss"))
						.append(baseApp);
			} else {
				contentDisposition.append(CommonUtil.dateToString(new Date(),
						"yyyy-MM-dd HH:mm:ss"));
			}

			response.setHeader("Content-disposition",
					contentDisposition.toString());

			response.setHeader("Content-Length",
					String.valueOf(downFile.length()));
			bis = new BufferedInputStream(new FileInputStream(downFile));

			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error(e);
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
}
