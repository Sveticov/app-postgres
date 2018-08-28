package com.sveticov.apppostgres.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.tools.Tool;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter

@Table(name = "workers")
@Entity
public class Workers implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id_workers;
    @Pattern(regexp = "[a-zA-Z]+", message = "name error")
//    @Size(min = 2, max = 4)
    String name_worker;
    @Pattern(regexp = "[a-zA-Z]+", message = "last name error")
//    @Size(min = 2, max = 4)
    String last_name_worker;
    @Email
    String email_worker;

    @ManyToMany( cascade = CascadeType.ALL)//
    @JoinTable(name = "workers_tools",joinColumns = @JoinColumn(name ="id_wt",referencedColumnName = "id_workers"),
    inverseJoinColumns = @JoinColumn(name = "id_tw",referencedColumnName = "id_tools"))
    private Set<Tools> tools;


public String toString(){
    String result="",result2="";

    result="name_worker "+this.name_worker+" last_name_worker "+this.last_name_worker+" email_worker "+this.email_worker;

    for(Tools tools:this.tools){
        result2=result2+tools.toString();

    }

    return result+"  "+result2;
}

    public Workers() {
    }

    public Workers(@Pattern(regexp = "[a-zA-Z]+", message = "name error") String name_worker, @Pattern(regexp = "[a-zA-Z]+", message = "last name error") String last_name_worker, @Email String email_worker, Set<Tools> tools) {
        this.name_worker = name_worker;
        this.last_name_worker = last_name_worker;
        this.email_worker = email_worker;
        this.tools = tools;
    }

    public Workers(@Pattern(regexp = "[a-zA-Z]+", message = "name error") String name_worker, @Pattern(regexp = "[a-zA-Z]+", message = "last name error") String last_name_worker, @Email String email_worker) {
        this.name_worker = name_worker;
        this.last_name_worker = last_name_worker;
        this.email_worker = email_worker;
    }
}
