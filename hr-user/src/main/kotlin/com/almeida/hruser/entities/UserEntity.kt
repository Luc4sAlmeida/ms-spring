package com.almeida.hruser.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name="tb_user")
data class UserEntity(

    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,

    @Column(unique = true)
    var email: String,
    var password: String,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name="tb_user_role",
        joinColumns = [JoinColumn(name ="user_id")],
        inverseJoinColumns = [JoinColumn(name= "role_id")]
    )
    val roles: Set<RoleEntity> = hashSetOf()

) : Serializable