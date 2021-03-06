package com.sharehistory.wechat.wechatofficalaccount.access;

import com.sharehistory.wechat.wechatofficalaccount.access.util.EncryptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Package-name:com.sharehistory.wechat.publicaccess.access
 * Time:2020/3/7-9:56.
 * Autor:computer-Administrator--zhangtianxiao
 * Note:GOOD PROGRAMER.
 */

@RestController
public class WechatPublicNumberAccess {

    /**
     * 微信公众号接入接口
     * @return
     */
    @GetMapping("/wechat/publicnumber/access/")
    public String access(ServletRequest servletRequest){
        String signature = servletRequest.getParameter("signature");
        String timestamp = servletRequest.getParameter("timestamp");
        String nonce = servletRequest.getParameter("nonce");
        String echostr = servletRequest.getParameter("echostr");
        String token = "ztx";

        // 对三个字段按字典排序
        ArrayList<String> storeList = new ArrayList<>();
        storeList.add(token);
        storeList.add(timestamp);
        storeList.add(nonce);
        Collections.sort(storeList);

        // 将排好序的三个字符串拼接成一个字符串
        StringBuilder sortedString = new StringBuilder();
        storeList.forEach(e->{
            sortedString.append(e);
        });


        // 用sh1加密
        try {
            String shaEncode = EncryptionUtil.shaEncode(sortedString.toString());
            if(shaEncode.equals(signature)){
                return echostr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "false";
    }

    @GetMapping("/interface/test/")
    public String test(ServletRequest servletRequest){

        return "hello word!!!";
    }

    @GetMapping("/wx/login")
    public String login(ServletRequest servletRequest){
       String code = servletRequest.getParameter("code");
       String phone = servletRequest.getParameter("phone");

       return "小程序服务器正常：" + code + "    === " + phone;
    }
}
