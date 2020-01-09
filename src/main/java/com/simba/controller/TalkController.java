package com.simba.controller;

import com.alibaba.fastjson.JSON;
import com.simba.model.RequestBean;
import com.simba.model.RequestBean.PerceptionBean;
import com.simba.model.RequestBean.PerceptionBean.InputImageBean;
import com.simba.model.RequestBean.PerceptionBean.InputTextBean;
import com.simba.model.RequestBean.PerceptionBean.SelfInfoBean;
import com.simba.model.RequestBean.UserInfoBean;
import com.simba.model.ResponseBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.Null;
import lombok.extern.log4j.Log4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/talkrobot")
@Log4j
public class TalkController {

  private OkHttpClient okHttpClient;

  private static final String API_KEY = "0c19133c707740b19e0da5e7542288cc";
  private static final String API_URL  = "http://openapi.tuling123.com/openapi/api/v2";
  private static final String USER_ID  = "543949";
  private MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
  private List<String> history = new ArrayList<>();

  @RequestMapping("/start")
  public String start(ModelMap modelMap) {
    log.info("start");
    modelMap.put("list",history);
    return "/talk/talk";
  }

  @RequestMapping("/sendMessage")
  @ResponseBody
  public String sendMessage(String text) throws Exception{
    this.history.add("傻不拉几:-->"+text);
    RequestBean requestBean = geneRequestJson(text);
    return sendPostRequestSync(requestBean);
  }

  public RequestBean geneRequestJson(String text){
    RequestBean requestBean = new RequestBean();
    UserInfoBean userInfoBean = new UserInfoBean();
    userInfoBean.setApiKey(API_KEY);
    userInfoBean.setUserId(USER_ID);
    PerceptionBean perceptionBean = new PerceptionBean();
    perceptionBean.setInputImage(new InputImageBean());
    perceptionBean.setInputText(new InputTextBean(text));
    perceptionBean.setSelfInfo(new SelfInfoBean());
    requestBean.setPerception(perceptionBean);
    requestBean.setReqType(0);
    requestBean.setUserInfo(userInfoBean);
    return  requestBean;
  }

  /**
   * 发送同步请求
   * @param requestBean
   * @return
   * @throws Exception
   */
  public String sendPostRequestSync(RequestBean requestBean) throws Exception{
    List<String> list =  new ArrayList<>();
    initOKHttp();
    String requestJson = JSON.toJSONString(requestBean);
    RequestBody requestBody = RequestBody.create(mediaType,requestJson);
    log.info("requestJson:"+requestJson);
    log.info("requestBody:"+requestBody.contentType()+":"+requestBody.contentLength());
//    Headers headers = new Headers.Builder().add("test","12").build();
    Request request = new Request.Builder()
        .url(API_URL)
//        .headers(headers)
        .post(requestBody)
        .build();
    log.info("request:"+request.toString());
    //异步请求enqueue 同步请求 execute
    Response response= okHttpClient.newCall(request).execute();
    log.info("response:"+response);
    ResponseBean responseBean = JSON.parseObject(response.body().string(),ResponseBean.class);
    String responseText = responseBean.getResults().get(0).getValues().getText();
    this.history.add("小犀牛:-->"+responseText);
    return responseBean.getResults().get(0).getValues().getText();
  }

  /**
   * 发送异步请求
   * @param requestBean
   * @return
   * @throws Exception
   */
  public void sendPostRequestAsync(RequestBean requestBean) throws Exception{
    List<String> list =  new ArrayList<>();
    initOKHttp();
    String requestJson = JSON.toJSONString(requestBean);
    RequestBody requestBody = RequestBody.create(mediaType,requestJson);
    log.info("requestJson:"+requestJson);
    log.info("requestBody:"+requestBody.contentType()+":"+requestBody.contentLength());
    Request request = new Request.Builder()
        .url(API_URL)
//        .headers(headers)
        .post(requestBody)
        .build();
    //异步请求enqueue
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        log.info("error:"+e.getMessage());
      }
      @Override
      public void onResponse(Call call, Response response) throws IOException, NullPointerException {
        //注意：这里的response下的string方法只能使用一次，就close了
//        log.info("success:"+response.body().string());
        //异步执行，这里应该在成功之后 修改对应的数据
      }
    });
  }

  private void initOKHttp(){
    OkHttpClient.Builder builder = new OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS);
    okHttpClient = builder.build();
  }
}
