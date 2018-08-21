package xx.wechat.tools.servlet;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import xx.wechat.tools.WechatContext;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.MessageControllerNotFoundException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.handler.ReceiveHandler;
import xx.wechat.tools.utils.Access;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DispatcherServlet extends HttpServlet {

    /**
     * 接入时配置的token
     */
    private String token;

    /**
     * 处理器
     **/
    private ReceiveHandler receiveHandler;

    public DispatcherServlet() {
    }

    @Override
    public void init() throws ServletException {
        super.init();
        ServletConfig servletConfig = this.getServletConfig();
        String appid = servletConfig.getInitParameter("appid");
        String secret = servletConfig.getInitParameter("secret");
        this.token = servletConfig.getInitParameter("token");
        String messageControllerPackage = servletConfig.getInitParameter("messageControllerPackage");
        if (StringUtils.isEmpty(appid) || StringUtils.isEmpty(secret) || StringUtils.isEmpty(token)) {
            throw new ServletException("Init servlet failed: missing necessary configuration information (appid|secret|token)+");
        }
        //初始化WechatContext
        try {
            this.getServletContext().setAttribute("wechatContext", new WechatContext(appid, secret));
            this.receiveHandler = new ReceiveHandler(messageControllerPackage);
        } catch (HttpException | WechatException | IOException | ClassNotFoundException e) {
            throw new ServletException("Init servlet failed: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接入认证
        String timestamp = req.getParameter("timestamp");
        String signature = req.getParameter("signature");
        String nonce = req.getParameter("nonce");
        if (Access.checkSignature(this.token, timestamp, nonce, signature)) {
            //接入成功
            resp.getWriter().write(req.getParameter("echostr"));
        } else {
            //接入失败
            resp.getWriter().write("+_+");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.setCharacterEncoding("UTF-8");
            this.receiveHandler.handle(req, resp);
        } catch (MessageControllerNotFoundException | IllegalAccessException | DocumentException | InstantiationException | InvocationTargetException e) {
            throw new ServletException("Request handle failed: " + e.getMessage());
        }
    }
}
