package com.simba.model;

import java.io.InputStream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * reqType : 0 perception : {"inputText":{"text":"附近的酒店"},"inputImage":{"url":"imageUrl"},"selfInfo":{"location":{"city":"北京","province":"北京","street":"信息路"}}}
 * userInfo : {"apiKey":"","userId":""}
 */
@Getter
@Setter
@ToString
public class RequestBean {

  private int reqType;
  private PerceptionBean perception;
  private UserInfoBean userInfo;

  @Getter
  @Setter
  @ToString
  public static class PerceptionBean {

    /**
     * inputText : {"text":"附近的酒店"} inputImage : {"url":"imageUrl"} selfInfo :
     * {"location":{"city":"北京","province":"北京","street":"信息路"}}
     */

    private InputTextBean inputText;
    private InputImageBean inputImage;
    private SelfInfoBean selfInfo;

    @Getter
    @Setter
    @ToString
    public static class InputTextBean {

      /**
       * text : 附近的酒店
       */

      private String text;

      public InputTextBean(String text){
        this.text = text;
      }

    }

    @Getter
    @Setter
    @ToString
    public static class InputImageBean {

      /**
       * url : imageUrl
       */

      private String url;
    }

    @Getter
    @Setter
    @ToString
    public static class SelfInfoBean {

      /**
       * location : {"city":"北京","province":"北京","street":"信息路"}
       */

      private LocationBean location;

      @Getter
      @Setter
      @ToString
      public static class LocationBean {

        /**
         * city : 北京 province : 北京 street : 信息路
         */

        private String city;
        private String province;
        private String street;
      }
    }
  }

  @Getter
  @Setter
  @ToString
  public static class UserInfoBean {

    /**
     * apiKey : userId :
     */

    private String apiKey;
    private String userId;

  }
}
