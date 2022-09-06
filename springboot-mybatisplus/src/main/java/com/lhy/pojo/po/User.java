package com.lhy.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author liheyan
 * @since 2022-09-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * user的nt_account用来登录系统的帐号
     */
    private String ntAccount;

    /**
     * 名字
     */
    private String amFirstname;

    /**
     * 姓
     */
    private String amLastname;

    /**
     * 是否有上级，选择AM时，选择了上级=1，否则=0
     */
    private Integer isMgr;

    /**
     * 权限组
     */
    private Integer roleId;

    /**
     * 入职/离职
     */
    private String amStatus;

    /**
     * 邮件地址
     */
    private String email;

    /**
     * 邮件抄送地址
     */
    private String emailCcGroup;

    /**
     * 国家
     */
    private String amRegion;

    /**
     * 城市
     */
    private String amProvince;

    /**
     * 添加时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
