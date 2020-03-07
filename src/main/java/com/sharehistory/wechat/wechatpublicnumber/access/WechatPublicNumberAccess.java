package com.sharehistory.wechat.wechatpublicnumber.access;

import com.sharehistory.wechat.wechatpublicnumber.access.util.EncryptionUtil;
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

        // 对三个字段按字典排序
        ArrayList<String> storeList = new ArrayList<>();
        storeList.add(signature);
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
                return "true";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "false";
    }
}
