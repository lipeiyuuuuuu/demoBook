package com.example.bootredisdemo.service;

/**
 * ClassName: VerificationCodeService
 * Description: 验证码
 * date:  2023-03-28  08:48
 *
 * @author lipeiyu
 * @version 1.0
 */
public interface SixNumberVerificationCodeService {

    /**
     * 生成验证码
     *
     * @return {@link String}
     */
    String generateVerificationCode();

}
