package com.jone.controller.common;

public class Constant {

    /**
     * 电站图片
     */
    public final static String doctype_station_img = "1001";
    /**
     * 规范化资料
     */
    public final static String doctype_station_standard = "1002";

    /**
     * 审核标志：未审核
     */
    public final static int flag_audit_none = 0;
    /**
     * 审核标志：审核通过
     */
    public final static int flag_audit_pass = 1;
    /**
     * 审核标志：审核不通过
     */
    public final static int flag_audit_no = 2;
    /**
     * 申请删除标志
     */
    public final static int flag_data_del = 3;
    /**
     * 已删除标志
     */
    public final static int flag_data_delete = 4;
    /**
     * 申请修改标志
     */
    public final static int flag_data_update = 5;



    /**
     * 操作标志：删除
     */
    public final static int operation_delete = 0;
    /**
     * 操作标志：新增
     */
    public final static int operation_insert = 1;
    /**
     * 操作标志：更新
     */
    public final static int operation_update = 2;
}

