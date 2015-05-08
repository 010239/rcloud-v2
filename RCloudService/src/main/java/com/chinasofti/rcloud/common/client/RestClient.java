package com.chinasofti.rcloud.common.client;

import com.chinasofti.rcloud.common.RCloudRestException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.IOException;

/**
 * @author DongZhukai
 * @date 14-7-2.
 */
public class RestClient {
  private HttpClient httpClient;
  private OAuth2AccessToken token;

  public RestClient(OAuth2AccessToken token) {
    httpClient = new DefaultHttpClient();
    this.token = token;
  }

  //public void doGet(String url) throws IOException {
  //  HttpGet getRequest = new HttpGet(url);
  //  getRequest.addHeader("Authorization",token.getValue());
  //  HttpResponse response = httpClient.execute(getRequest);
  //
  //  if(response.getStatusLine().getStatusCode() != 200) {
  //    throw new RCloudRestException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
  //  }
  //}

  /**
   * 不检查token的有效性。
   *
   * @param url
   * @param token
   */
  public void doPost(String url, OAuth2AccessToken token) {
    HttpPost postMethod = new HttpPost(url);

    String header = token.getTokenType() + " " + token.getValue();
    postMethod.addHeader("Authorization",header);
  }
}
