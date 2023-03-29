package com.example.bootredisdemo.service;

import demobook.common.CommonResultBean;

/**
 * ClassName: VerificationCodeService
 * Description:
 * date:  2023-03-28  11:17
 *
 * @author lipeiyu
 * @version 1.0
 */
public interface VerificationCodeService {
    /**
     * 得到六个数字验证码
     *
     * @param id id
     * @return {@link CommonResultBean}<{@link String}>
     */
    public CommonResultBean<String> getSixNumberVerificationCode(String id);

    /**
     * 验证六位数字验证码
     *
     * @param id   id
     * @param code 代码
     * @return {@link CommonResultBean}<{@link String}>
     */
    public CommonResultBean<Integer> VerificateSixNumberCode(String id,String code);
}
