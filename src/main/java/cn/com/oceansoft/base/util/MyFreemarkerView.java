package cn.com.oceansoft.base.util;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by smc on 2015/8/5.
 * 解决freemarker的绝对路径的问题
 */
public class MyFreemarkerView extends FreeMarkerView {

    private static final Logger log = LoggerFactory.getLogger(MyFreemarkerView.class);
    private static final String BASE_CONTEXT="base";

    @Override
    protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
        log.debug("freemarker的基本路径:{}",BASE_CONTEXT);
        model.put(BASE_CONTEXT,request.getContextPath());
//        model.put("token",UuidUtils.getUpperUuid());
        super.exposeHelpers(model, request);
    }
}
