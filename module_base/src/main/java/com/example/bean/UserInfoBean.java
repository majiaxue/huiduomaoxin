package com.example.bean;

import java.io.Serializable;

public class UserInfoBean implements Serializable {

    /**
     * id : 21
     * userCode : 293644338167021568
     * username : 13523565115
     * password : $2a$10$l.wVKFaj5QNI2Nrem0V.c.KuKzgECkFkcwwmbMCSDdzmNA0LZ8s2u
     * nickname : 张三
     * phone : 13523565115
     * status : null
     * createTime : 2019-05-29 17:24:36
     * icon : null
     * gender : null
     * birthday : null
     * city : null
     * job : null
     * personalizedSignature : 6666666
     * sourceType : null
     * integration : null
     * growth : null
     * luckeyCount : null
     * historyIntegration : null
     * weixinOpenid : oC5Gu5k8hOSlrbXkYgh-YDHkva48
     * inviteCode : Q5CJqOgb
     * blance : null
     * sellerId : null
     * level : null
     * checkCode : null
     * newPassword : null
     * oldPhone : null
     */

    private String id;
    private String userCode;
    private String username;
    private String password;
    private String nickname;
    private String phone;
    private String status;
    private String createTime;
    private String icon;
    private String gender;
    private String birthday;
    private String city;
    private String job;
    private String personalizedSignature;
    private String sourceType;
    private String integration;
    private String growth;
    private String luckeyCount;
    private String historyIntegration;
    private String weixinOpenid;
    private String inviteCode;
    private String blance;
    private String sellerId;
    private String level;
    private String checkCode;
    private String newPassword;
    private String oldPhone;
    private String token;
    private String oldPassword;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUsername() {
        return username;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getGrowth() {
        return growth;
    }

    public void setGrowth(String growth) {
        this.growth = growth;
    }

    public String getLuckeyCount() {
        return luckeyCount;
    }

    public void setLuckeyCount(String luckeyCount) {
        this.luckeyCount = luckeyCount;
    }

    public String getHistoryIntegration() {
        return historyIntegration;
    }

    public void setHistoryIntegration(String historyIntegration) {
        this.historyIntegration = historyIntegration;
    }

    public String getWeixinOpenid() {
        return weixinOpenid;
    }

    public void setWeixinOpenid(String weixinOpenid) {
        this.weixinOpenid = weixinOpenid;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getBlance() {
        return blance;
    }

    public void setBlance(String blance) {
        this.blance = blance;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id='" + id + '\'' +
                ", userCode='" + userCode + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", createTime='" + createTime + '\'' +
                ", icon='" + icon + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", city='" + city + '\'' +
                ", job='" + job + '\'' +
                ", personalizedSignature='" + personalizedSignature + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", integration='" + integration + '\'' +
                ", growth='" + growth + '\'' +
                ", luckeyCount='" + luckeyCount + '\'' +
                ", historyIntegration='" + historyIntegration + '\'' +
                ", weixinOpenid='" + weixinOpenid + '\'' +
                ", inviteCode='" + inviteCode + '\'' +
                ", blance='" + blance + '\'' +
                ", sellerId='" + sellerId + '\'' +
                ", level='" + level + '\'' +
                ", checkCode='" + checkCode + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", oldPhone='" + oldPhone + '\'' +
                ", token='" + token + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                '}';
    }
}

