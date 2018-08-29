package xx.wechat.tools.servlet;

import com.alibaba.fastjson.JSON;
import xx.wechat.tools.WechatContext;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

/**
 * JS接口调用临时票据获取
 */
public class JsApiTicketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String url = req.getParameter("url");
            url = URLDecoder.decode(url, "UTF-8");
            //移除"#"及其后面内容
            int index = url.indexOf("#");
            if (index > 0) {
                url = url.substring(0, index);
            }
            WechatContext wechatContext = (WechatContext) req.getServletContext().getAttribute("wechatContext");
            Map<String, String> signature = wechatContext.getJsApiTicketSignature(url);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().write(JSON.toJSONString(signature));
            resp.flushBuffer();
        } catch (HttpException | WechatException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
