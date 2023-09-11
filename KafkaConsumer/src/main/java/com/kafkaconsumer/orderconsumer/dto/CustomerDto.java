package com.kafkaconsumer.orderconsumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private Long custId;
    private String firstname;
    private String lastname;
    private String phno;
    private String email;
}
