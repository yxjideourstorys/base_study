package com.study.logaop.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@TableName(value = "syslog")
@Accessors(chain = true)
public class SysLog {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 操作人
     */
    @TableField("operationUser")
    private String operationUser;

    /**
     * 请求路径
     */
    @TableField("path")
    private String path;

    /**
     * 方法执行时间
     */
    @TableField("time")
    private String time;

    /**
     * 方法入参
     */
    @TableField("parameter")
    private String parameter;

    /**
     * 操作方法
     */
    @TableField("title")
    private String title;

    /**
     * 方法描述
     */
    @TableField("action")
    private String action;

    /**
     * 系统类型
     */
    @TableField("sysType")
    private Integer sysType;

    /**
     * 操作类型
     */
    @TableField("opType")
    private Integer opType;

    public SysLog(String operationUser, String path, String time,
                  String parameter, String title, String action, Integer sysType, Integer opType) {
        super();
        this.operationUser = operationUser;
        this.path = path;
        this.time = time;
        this.parameter = parameter;
        this.title = title;
        this.action = action;
        this.sysType = sysType;
        this.opType = opType;
    }
}
