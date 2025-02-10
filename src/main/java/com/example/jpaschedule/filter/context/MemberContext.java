package com.example.jpaschedule.filter.context;

public class MemberContext {
    private static final ThreadLocal<Long> memberIdThreadLocal = new ThreadLocal<>();

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
