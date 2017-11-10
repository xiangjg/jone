package com.jone.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class BaseController {

    private Logger logger = LoggerFactory.getLogger(BaseController.class);

    public void printJson(Object object, HttpServletResponse response) {
        OutputStream out = null;
        try {
            response.setContentType("application/json" + ";charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            out = response.getOutputStream();
            out.write(JSON.toJSONString(object).getBytes("UTF-8"));
        } catch (IOException e) {
            logger.error("IOException",e);
        } finally {
            if (null != out) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    logger.error("IOException",e);
                }
            }
        }
    }
}
