package com.itzpy.entity.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("appConfig")
public class AppConfig {

    /**
     * websocket端口
     */
    @Value("${ws.port}")
    private Integer wsPort;

    /**
     * 项目路径
     */
    @Value("${project.folder}")
    private String projectFolder;

    /**
     * 管理员邮箱
     */
    @Value("${admin.emails}")
    private String adminEmails;

    public Integer getWsPort() {
        return wsPort;
    }

    public String getProjectFolder() {
        if(StringUtils.isEmpty(projectFolder)&&!projectFolder.endsWith("/")){
            projectFolder = projectFolder + "/";
        }
        return projectFolder;
    }

    public String getAdminEmails() {
        return adminEmails;
    }
}
