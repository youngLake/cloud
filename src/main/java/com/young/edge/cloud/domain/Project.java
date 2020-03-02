package com.young.edge.cloud.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tornodo Young
 * @date 2020/3/2 18:15
 */
@Table(name = "project")
@Entity
@Getter
@Setter
public class Project {
    @Id
    @Column
    private String id;
    @Column
    private String name;
    @Column
    private String detail;
    @Column
    private String userId;
    @Column
    private Integer status;
}
