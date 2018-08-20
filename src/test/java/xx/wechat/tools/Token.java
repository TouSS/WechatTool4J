package xx.wechat.tools;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Access;

import java.io.*;

public class Token {
    public static AccessToken getAccessToken() throws WechatException, HttpException, IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File file = new File(tempDir + "wx_token");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tokenString = reader.readLine();
        AccessToken accessToken;
        if (StringUtils.isNotEmpty(tokenString)) {
            accessToken = JSON.parseObject(tokenString, AccessToken.class);
            if (!accessToken.isExpired()) {
                return accessToken;
            }
        }
        accessToken = Access.getAccessToken("appid", "appsecret");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.write(JSON.toJSONString(accessToken));
        return accessToken;
    }
}
