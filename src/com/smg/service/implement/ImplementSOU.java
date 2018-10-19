package com.smg.service.implement;

import com.smg.dao.UsrDao;
import com.smg.dao.implement.ImplementUD;
import com.smg.pojo.UserInfo;
import com.smg.service.ServiceOfUsr;

public class ImplementSOU implements ServiceOfUsr {

    private UsrDao ud = new ImplementUD();

    @Override
    public UserInfo login(UserInfo user) {
        if (user.getUsrName() == null || "".equals(user.getUsrName()) || user.getUsrPwd() == null
                || "".equals(user.getUsrPwd())) {
            return null;
        }
        return ud.login(user);
    }

    @Override
    public UserInfo checkRegisterName(String registerName) {
        return ud.checkRegisterName(registerName);
    }

}
