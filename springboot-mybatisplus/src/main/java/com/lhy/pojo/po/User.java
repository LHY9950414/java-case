package com.lhy.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @ApiModelProperty(value = "user的nt_account用来登录系统的帐号")
    private String ntAccount;

    @ApiModelProperty(value = "名字")
    private String amFirstname;

    @ApiModelProperty(value = "姓")
    private String amLastname;

    @ApiModelProperty(value = "是否有上级，选择AM时，选择了上级=1，否则=0")
    private Integer isMgr;

    @ApiModelProperty(value = "权限组")
    private Integer roleId;

    @ApiModelProperty(value = "入职/离职")
    private String amStatus;

    @ApiModelProperty(value = "邮件地址")
    private String email;

    @ApiModelProperty(value = "邮件抄送地址")
    private String emailCcGroup;

    @ApiModelProperty(value = "国家")
    private String amRegion;

    @ApiModelProperty(value = "城市")
    private String amProvince;

    @ApiModelProperty(value = "添加时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
