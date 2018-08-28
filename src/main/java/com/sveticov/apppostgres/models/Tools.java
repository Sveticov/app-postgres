package com.sveticov.apppostgres.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tools")
public class Tools implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_tools;
    private String name_tools;
    private String deta_tools;
    @ToString.Exclude
    @ManyToMany( mappedBy = "tools")
    private Set<Workers> workers;

    public Tools() {
    }


    public Tools(String name_tools, String deta_tools, Set<Workers> workers) {
        this.name_tools = name_tools;
        this.deta_tools = deta_tools;
        this.workers = workers;
    }

    public Tools(String name_tools, String deta_tools) {
        this.name_tools = name_tools;
        this.deta_tools = deta_tools;
    }
}
