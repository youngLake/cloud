package com.young.edge.cloud.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:04
 */
@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order {
    @Id
    @Column
    private String id;
    @Column
    private String typeCode;
    @Column
    private String typeName;
    @Column
    private BigDecimal amout;
    @Column
    private Integer status;
    @Column
    private String detail;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @Column
    private String userId;
    @Column
    private String projectId;
    @Column
    private String buyer;
    @Column
    private String phone;
    @Column
    private String gender;
}
