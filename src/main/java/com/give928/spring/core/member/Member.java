package com.give928.spring.core.member;

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
