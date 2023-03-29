package com.example.bootredisdemo.service.impl;

import com.example.bootredisdemo.service.SixNumberVerificationCodeService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * ClassName: VerificationCodeServiceImpl
 * Description:
 * date:  2023-03-28  08:48
 *
 * @author lipeiyu
 * @version 1.0
 */
@Service
public class SixNumberVerificationCodeServiceImpl implements SixNumberVerificationCodeService {


    /**
     * 生成六位纯数字验证码
     *
     * @return {@link String}
     */
    @Override
    public  String generateVerificationCode() {
        Random random = new Random();
        // 随机生成100000到999999之间的整数
        int verificationCodeInt = random.nextInt(900000) + 100000;
        // 将整数转换为字符串并返回
        return Integer.toString(verificationCodeInt);
    }


}
