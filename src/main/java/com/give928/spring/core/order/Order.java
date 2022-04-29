package com.give928.spring.core.order;

import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }
}
