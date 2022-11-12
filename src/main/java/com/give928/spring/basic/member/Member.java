package com.give928.spring.basic.member;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Member {
    private Long id;
    private String name;
    private Grade grade;
}
