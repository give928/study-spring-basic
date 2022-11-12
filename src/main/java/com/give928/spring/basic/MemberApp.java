package com.give928.spring.basic;

import com.give928.spring.basic.member.Grade;
import com.give928.spring.basic.member.Member;
import com.give928.spring.basic.member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl();

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = Member.builder()
                .id(1L)
                .name("memberA")
                .grade(Grade.VIP)
                .build();
        memberService.join(member);

        Member findMember = memberService.findMember(1L);

        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
    }
}
