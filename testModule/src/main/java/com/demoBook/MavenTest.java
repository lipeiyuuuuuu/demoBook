package com.demoBook;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: MavenTest
 * Description:
 * date:  2023-03-21  08:30
 *
 * @author lipeiyu
 * @version 1.0
 */
public class MavenTest {
    /*测试引用父工程的方法*/
    public void testParentMethod(){
        Tools tools = new Tools();
        tools.tool1();

        List<String> list = new ArrayList<>();
        list.add("123");
        JSONUtil.toJsonStr(list);
    }
}
