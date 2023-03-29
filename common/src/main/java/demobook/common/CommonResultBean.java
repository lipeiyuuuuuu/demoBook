package demobook.common;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 前后端交互通用结果对象
 *
 * @author Administrator
 * @date 2023/03/28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResultBean<T> implements Serializable {
    public static final long serialVersionUID = 1L;
    /**
     * 0 success, 1 fail
     */
    private int isOK;

    /**
     * 消息, 通常是一个提示
     */
    private String msg = "失败";

    /**
     * 返回的数据
     */
    private T data;

    /**
     * 默认成功msg
     */
    public static final String defaultSuccessMsg = "success";

    /**
     * 默认失败msg
     */
    public static final String defaultFailMsg = "fail";

    /**
     * 成功
     *
     * @param msg  味精
     * @param data 数据
     * @return {@link CommonResultBean}<{@link T}>
     */
    public CommonResultBean<T> success(String msg, T data) {
        if (msg == null) {
            return new CommonResultBean<T>(Const.CODE_OF_SUCCESS, defaultSuccessMsg, data);
        } else {
            return new CommonResultBean<T>(Const.CODE_OF_SUCCESS, msg, data);
        }
    }


    /**
     * 失败
     *
     * @param msg  味精
     * @param data 数据
     * @return {@link CommonResultBean}<{@link T}>
     */
    public CommonResultBean<T> fail(String msg, T data) {
        if (msg == null) {
            return new CommonResultBean<T>(Const.CODE_OF_FAIL, defaultFailMsg, data);
        } else {
            return new CommonResultBean<T>(Const.CODE_OF_FAIL, msg, data);
        }
    }

    /**
     * 失败
     *
     * @param msg 味精
     * @return {@link CommonResultBean}<{@link T}>
     */
    public CommonResultBean<T> fail(String msg) {
        if (msg == null) {
            return new CommonResultBean<T>(Const.CODE_OF_FAIL, defaultFailMsg, null);
        } else {
            return new CommonResultBean<T>(Const.CODE_OF_FAIL, msg, null);
        }
    }


    /**
     * 失败
     *
     * @return {@link CommonResultBean}<{@link T}>
     */
    public CommonResultBean<T> fail() {
        this.isOK = Const.CODE_OF_FAIL;
        this.msg = defaultFailMsg;
        this.data = null;
        return new CommonResultBean<T>(Const.CODE_OF_FAIL, defaultFailMsg,null);
    }

}
