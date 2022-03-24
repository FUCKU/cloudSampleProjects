package com.cloud.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Robert_Li
 */
@RefreshScope
@RestController
public class TestController {
    @Autowired
    private Environment environment;

    @GetMapping(value = "/getParameter")
    public String goUploadImg(@RequestParam String param) {
        return environment.getProperty(param);
    }

}
