package xx.wechat.tools.servlet;

import xx.wechat.tools.WechatContext;
import xx.wechat.tools.bean.WebAccessToken;
import xx.wechat.tools.context.WebAccessContext;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 网页授权验证
 */
public class WebAccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        try {
            WechatContext wechatContext = (WechatContext) req.getServletContext().getAttribute("wechatContext");
            //检查session是否已经验证过
            HttpSession session = req.getSession();
            WebAccessToken webAccessToken = (WebAccessToken) session.getAttribute("webAccessToken");
            WebAccessContext webAccessContext = wechatContext.getWebAccessContext();
            String code = req.getParameter("code");
            if (webAccessToken == null || webAccessToken.isRefreshExpired()) {
                //还未验证过或刷新码过期
                webAccessToken = webAccessContext.getWebAccessToken(code);
                session.setAttribute("webAccessToken", webAccessContext);
                if ("snsapi_userinfo".equals(webAccessToken.getScope())) {
                    session.setAttribute("wx_user", webAccessContext.getUserInfo(webAccessToken, "zh_CN"));
                }
            } else {
                //不为空， 是否过期 过期刷新
                if (webAccessToken.isExpired()) {
                    webAccessContext.refreshWebAccessToken(webAccessToken);
                }
            }
            //验证完成
            filterChain.doFilter(req, resp);
        } catch (HttpException | WechatException e) {
            //验证失败，访问受限
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }

    @Override
    public void destroy() {

    }
}
