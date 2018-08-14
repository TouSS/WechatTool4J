package xx.wechat.tools.servlet;

import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.WechatContext;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Access;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DispatcherServlet extends HttpServlet {
    /** 公众号APPID **/
    private String appid;
    /** 公众号密匙 **/
    private String secret;
    /** 公众号接入时填写的TOKEN **/
    private String token;

    public DispatcherServlet() {
    }

    public DispatcherServlet(String appid, String secret, String token) {
        this.appid = appid;
        this.secret = secret;
        this.token = token;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        if(StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret) || StringUtils.isEmpty(token)) {
            throw new ServletException("Init servlet failed: missing necessary configuration information (appid|secret|token)+");
        }
        //初始化WechatContext
        try {
            this.getServletContext().setAttribute("wechatContext", new WechatContext(appid, secret));
        } catch (Exception e) {
            throw new ServletException("Init servlet failed: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接入认证
        String timestamp = req.getParameter("timestamp");
        String signature = req.getParameter("signature");
        String nonce = req.getParameter("nonce");
        if(Access.checkSignature(this.token, timestamp, nonce, signature)) {
            //接入成功
            resp.getWriter().write(req.getParameter("echostr"));
        } else {
            //接入失败
            resp.getWriter().write(req.getParameter("+_+"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(req.getInputStream(), "UTF-8"));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line);
        }


    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
