package com.longpengz.library.modle.constants;

/**
 * 请求地址前缀常量
 * @author longpengZ
 */
public class RestPrefixConstant {

    /**
     * 管理后台
     */
    public final static String  MANAGE_REST_PREFIX = "/manage";

    /**
     * 后台通用
     */
    public final static String MANAGE_COMMON_PREFIX =  MANAGE_REST_PREFIX + "/common";

    /**
     * 后台文章管理
     */
    public final static String MANAGE_ARTICLE_MANAGEMENT_PREFIX =  MANAGE_REST_PREFIX + "/articleManagement";

    /**
     * 后台系统管理
     */
    public final static String MANAGE_SYSTEM_PREFIX =  MANAGE_REST_PREFIX + "/system";

    /**
     * 后台系统管理
     */
    public final static String MANAGE_USER_MANAGEMENT_PREFIX =  MANAGE_REST_PREFIX + "/userManagement";

    /**
     * 后台通用
     */
    public final static String MANAGE_DICTIONATY_MANAGEMENT_PREFIX =  MANAGE_REST_PREFIX + "/dictionatyManagement";

    /**
     * 应用
     */
    public final static String APP_PREFIX = "/api";

    /**
     * 应用通用
     */
    public final static String APP_COMMON_PREFIX =  APP_PREFIX + "/common";


    /**
     * 文章相关
     */
    public final static String APP_ARTICLE_PREFIX =  APP_PREFIX + "/article";

    /**
     * 评论相关
     */
    public final static String APP_COMMENT_PREFIX = APP_PREFIX + "/comment";

    /**
     * 用户相关
     */
    public final static String APP_USER_PREFIX = APP_PREFIX + "/user";

}
