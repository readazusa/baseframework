package cn.com.oceansoft.sys.user.model;

import cn.com.oceansoft.base.entity.BaseInfo;
import cn.com.oceansoft.base.util.DateFormatUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by 念梓  on 2017/6/8.
 * Email:sunmch@163.com
 * author: 念梓
 * des:
 */
public class UserInfo extends BaseInfo{

    private String username;

    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    private String sex="女";  //

    private String deptCode;

    private String deptName;

    private String mobile;

    private String phone;  //办公室电话号码

    private String position;  //职位

    private String flag="0";  //状态 0：正常  1：禁用

    private String birthdayStr;


    private String roleIds;


    public String getUsername() {
            return  username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBirthdayStr() {
        return DateFormatUtils.getFormatDate(this.getBirthday(),"yyyy-MM-dd HH:mm:ss");
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }


    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
