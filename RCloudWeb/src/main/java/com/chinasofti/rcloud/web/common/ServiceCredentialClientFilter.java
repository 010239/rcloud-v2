package com.chinasofti.rcloud.web.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.chinasofti.rcloud.common.MD5;

public class ServiceCredentialClientFilter implements Filter {

	private FilterConfig filterConfig;
    private String encoding = null;
    private String path = null;
    
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest rqt = (HttpServletRequest) request;
		
		String accessKeyId=rqt.getParameter("accessKeyId");
		String desStr=rqt.getParameter("sign");
		String jsonStr="";
		
		int length=rqt.getContentLength();
		if(length>0){
			InputStream is=rqt.getInputStream();
			byte[] buffer=new byte[length];
			int pad=0;
			while(pad<length){
				pad+=is.read(buffer,pad,length);
			}
			jsonStr=new String(buffer);
		} 
		if(!jsonStr.equals("")&&jsonStr.length()!=0){
			jsonStr=MD5.GetMD5Code(accessKeyId+jsonStr);
		}
		
		//  rest/useServiceCredential/verifyServiceCredential/{jsonStr}/{accessKeyId}/{desStr}
		
		StringBuffer sb = new StringBuffer("rest/useServiceCredential/verifyServiceCredential/");
		sb.append(jsonStr).append("/").append(accessKeyId).append("/").append(desStr);
		
		
        @SuppressWarnings({ "deprecation", "resource" })
		HttpClient httpclient = new DefaultHttpClient();  
        HttpPost httpgets = new HttpPost(path+sb);    
        HttpResponse responsea = httpclient.execute(httpgets);    
        HttpEntity entity = responsea.getEntity();    
        if (entity != null) {    
            InputStream instreams = entity.getContent();    
            String str = convertStreamToString(instreams);  
            System.out.println("Do something");   
            System.out.println(str);
        }  
		
		chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
        this.path = filterConfig.getInitParameter("targetBeanName");
        System.out.println(path);
        System.out.println("初始化");

	}
	
	
	public static String convertStreamToString(InputStream is) {      
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));      
        StringBuilder sb = new StringBuilder();      
       
        String line = null;      
        try {      
            while ((line = reader.readLine()) != null) {  
                sb.append(line + "\n");      
            }      
        } catch (IOException e) {      
            e.printStackTrace();      
        } finally {      
            try {      
                is.close();      
            } catch (IOException e) {      
               e.printStackTrace();      
            }      
        }      
        return sb.toString();      
    }  
	

}
