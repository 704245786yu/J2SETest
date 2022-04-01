/*
 * Project: user_center_one
 * 
 * File Created at 2015-07-03
 
 * Copyright 2014 ANTEAM-INC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Anteam_INC Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with ANTEAM-INC.
 */
package J2SEClass.enumDemo;

/**
 * 医院等级
 *
 * @author zhiyu
 * @Date 2019-03-11
 */
public enum HospitalLevelEnum {
    C101(101, "一级丙等"),
    C102(102, "一级乙等"),
    C103(103, "一级甲等"),
    C104(104, "一级未定等"),
    C201(201, "二级丙等"),
    C202(202, "二级乙等"),
    C203(203, "二级甲等"),
    C204(204, "二级未定等"),
    C301(301, "三级丙等"),
    C302(302, "三级乙等"),
    C303(303, "三级甲等"),
    C304(304, "三级特等"),
    C305(305, "三级未定等"),
    C401(401, "未定级");

    private final Integer code;

    private final String msg;

    HospitalLevelEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsgByCode(int code) {
        HospitalLevelEnum e = HospitalLevelEnum.valueOf("C"+401);
        System.out.println(e);
        return e.msg;
    }

}
