package juzix.com.web3jdemo.bean;

import java.util.List;

/**
 * Created by 徐敏 on 2017/8/24.
 */

public class UserInfoResult {



    private String ret;
    private DataBean data;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<UserInfo> items;

        public List<UserInfo> getItems() {
            return items;
        }

        public void setItems(List<UserInfo> items) {
            this.items = items;
        }


    }
}
