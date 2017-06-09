package cn.com.oceansoft.base.shiro.filter;

import cn.com.oceansoft.base.shiro.token.MyUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * net.sunmingchun.www.shiro.filter
 * Created by smc
 * date on 2016/2/26.
 * Email:sunmch@163.com
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger log = LoggerFactory.getLogger(MyFormAuthenticationFilter.class);

    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (log.isTraceEnabled()) {
                    log.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (log.isTraceEnabled()) {
                    log.trace("Login page view.");
                }
                return true;
            }
        } else {
            if (log.isTraceEnabled()) {
                log.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }

    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }
    }

    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        String validatecode = request.getParameter("validatecode");
        MyUsernamePasswordToken myUsernamePasswordToken = new MyUsernamePasswordToken();
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) createToken(username, password, request, response);
        myUsernamePasswordToken.setRememberMe(usernamePasswordToken.isRememberMe());
        myUsernamePasswordToken.setUsername(usernamePasswordToken.getUsername());
        myUsernamePasswordToken.setValidatecode(validatecode);
        myUsernamePasswordToken.setHost(usernamePasswordToken.getHost());
        myUsernamePasswordToken.setPassword(usernamePasswordToken.getPassword());
//        return createToken(username, password, request, response);
        return myUsernamePasswordToken;
    }






}
