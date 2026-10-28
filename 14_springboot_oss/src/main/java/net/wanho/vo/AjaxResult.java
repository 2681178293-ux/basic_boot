package net.wanho.vo;

import java.util.HashMap;

public class AjaxResult extends HashMap {

    public AjaxResult(boolean success,int code,String msg,Object data){
        this.put("success",success);
        this.put("code",code);
        this.put("msg",msg);
        this.put("data",data);
    }

    public static AjaxResult success(){
        return new AjaxResult(true,200,"操作成功",null);
    }

    public static AjaxResult success(String msg,Object data){
        return new AjaxResult(true,200,msg,data);
    }

    public static AjaxResult success(Object data){
        return new AjaxResult(true,200,"操作成功",data);
    }

    public static AjaxResult error(){
        return new AjaxResult(false,500,"操作失败",null);
    }

    public static AjaxResult error(String msg){
        return new AjaxResult(false,500,msg,null);
    }

    public static AjaxResult error(String msg,Object data){
        return new AjaxResult(false,500,msg,data);
    }

    public static AjaxResult error(int code,String msg){
        return new AjaxResult(false,code,msg,null);
    }

    public static AjaxResult error(int code,String msg,Object data){
        return new AjaxResult(false,code,msg,data);
    }
}
