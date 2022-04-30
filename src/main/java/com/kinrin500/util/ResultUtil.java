package com.kinrin500.util;


public class ResultUtil {


    /**
     * 返回请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data) {
        return commonResult(200,"请求成功",data);
    }

    /**
     * 请求失败
     *
     * @param errorMsg :
     * @return : com.quickbusiness.utils.Result<T>
     * @author : niutd
     * @date : 2019/3/14 17:49
     */
    public static <T> Result<T> error(String errorMsg) {
        return error(1, errorMsg);
    }

    /**
     * 请求失败 -自定义
     *
     * @param errorCode :
     * @param errorMsg  :
     */
    public static <T> Result<T> error(int errorCode, String errorMsg) {
        return commonResult(errorCode, errorMsg, null);
    }

    /**
     * 请求结果
     *
     * @param Code :
     * @param Msg  :
     * @param data      :
     * @date : 2019/3/15 17:05
     */
    public static <T> Result<T> result(int Code, String Msg, T data) {
        return commonResult(Code, Msg, data);
    }


    public static <T> Result<T> commonResult(int Code,String message,T data){
        Result<T> result = new Result();
        result.setCode(Code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

}
