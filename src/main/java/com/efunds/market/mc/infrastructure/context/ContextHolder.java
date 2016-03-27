package com.efunds.market.mc.infrastructure.context;

/**
 * created by root 2016/3/27
 * 功能：
 */
public class ContextHolder {

    private static ThreadLocal<ContextInfo>  contextInfoHolder = new ThreadLocal<ContextInfo>();

    public static void setContextInfo(ContextInfo contextInfo){
        contextInfoHolder.set(contextInfo);
    }

    public static ContextInfo getContextInfo(){
        return contextInfoHolder.get();
    }

    public static class ContextInfo {
        //请求时间
        private long beginTime;

        public long getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(long beginTime) {
            this.beginTime = beginTime;
        }

    }
}
