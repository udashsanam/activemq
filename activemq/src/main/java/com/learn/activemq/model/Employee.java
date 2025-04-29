package com.learn.activemq.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee implements Serializable {

    private String name;

    private String address;
}
