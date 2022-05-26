package com.haley.teastusys.dto;

import lombok.Data;
//这个类的字段使用小写,不然可能RequestBody不能接收参数
@Data
public class Student {
    private String sno;
    private String sname;
    private String ssex;
    private int sage;
    private String sdept;


}
