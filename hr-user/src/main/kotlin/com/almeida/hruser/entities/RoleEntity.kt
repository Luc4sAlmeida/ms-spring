package com.almeida.hruser.entities

import javax.persistence.*

@Entity
@Table(name="tb_role")
data class RoleEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var roleName: String
)