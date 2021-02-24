package com.almeida.hroauth.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors

class UserEntity(
    private var id: Long = 0,
    private var email: String = "",
    private var password: String = "",
    roles: Set<RoleEntity> = hashSetOf()
) : UserDetails {

    private var auth: MutableCollection<out GrantedAuthority> =
        roles.stream().map { x -> SimpleGrantedAuthority(x.roleName) }.collect(Collectors.toList())

    fun hasAuthority(role: RoleEntity): Boolean {
        return authorities.contains(SimpleGrantedAuthority(role.roleName))
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return auth
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getId() : Long = this.id

    fun setUsername(email: String) {
        this.email = email
    }

    override fun getUsername(): String {
        return email
    }

    fun setPassword(password: String) {
        this.password = password
    }

    override fun getPassword(): String {
        return password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}