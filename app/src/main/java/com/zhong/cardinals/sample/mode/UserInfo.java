package com.zhong.cardinals.sample.mode;

/**
 * Created by zhong on 2017/3/28.
 */

public class UserInfo {
    private long userId;//": 307111,
    private String nick;//": "xxx",  //用户昵称
    private boolean isNew;//": false,  //只有老用户能用这个登录
    private String figureUrl;//": "some url",  //头像地址
    private boolean isPasswordSet;//": false  //是否已经设置密码
    private boolean isInfoSet;//" : true //用户基本信息是否已经设置

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public boolean isNew() {
        return isNew;
    }

    public void setNew(boolean aNew) {
        isNew = aNew;
    }

    public String getFigureUrl() {
        return figureUrl;
    }

    public void setFigureUrl(String figureUrl) {
        this.figureUrl = figureUrl;
    }

    public boolean isPasswordSet() {
        return isPasswordSet;
    }

    public void setPasswordSet(boolean passwordSet) {
        isPasswordSet = passwordSet;
    }

    public boolean isInfoSet() {
        return isInfoSet;
    }

    public void setInfoSet(boolean infoSet) {
        isInfoSet = infoSet;
    }
}


