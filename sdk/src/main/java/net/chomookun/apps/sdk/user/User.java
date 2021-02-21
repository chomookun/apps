package net.chomookun.apps.sdk.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "apps_user")
@Data
@Builder
public class User {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String password;

    @Column(name = "emal")
    private String email;

}
