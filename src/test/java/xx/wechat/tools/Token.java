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
        File file = new File(tempDir + "wx1f47e3c510d330dc");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tokenString = reader.readLine();
        reader.close();
        AccessToken accessToken;
        if (StringUtils.isNotEmpty(tokenString)) {
            accessToken = JSON.parseObject(tokenString, AccessToken.class);
            if (!accessToken.isExpired()) {
                return accessToken;
            }
        }
        accessToken = Access.getAccessToken("wx1f47e3c510d330dc", "13114dfaa18f61d04666b4f19364ce2f");
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
        bufferedWriter.write(JSON.toJSONString(accessToken));
        bufferedWriter.flush();
        bufferedWriter.close();
        return accessToken;
    }
}
