package com.sha.expo;

/**
 * 〈时间设置类〉
 *
 * @author mazhongrong@smeyun.com
 * @date 2018/5/25
 */

public class Mytime {
    public String callTime;

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    @Override
    public String toString() {
        return "Mytime{" +
                "callTime='" + callTime + '\'' +
                '}';
    }
}
