package com.example.jpaschedule.service.context;

public class MemberContext {
    private static ThreadLocal<Long> memberIdThreadLocal = new ThreadLocal<>();

    public static void setMemberId(Long memberId) {
        memberIdThreadLocal.set(memberId);
    }

    public static Long getMemberId() {
        return memberIdThreadLocal.get();
    }

    public static void clear() {
        memberIdThreadLocal.remove();
    }
}
