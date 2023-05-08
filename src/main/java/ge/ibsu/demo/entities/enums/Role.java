package ge.ibsu.demo.entities.enums;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.CUSTOMER_READ,Permission.CUSTOMER_ADD));

    private final Set<Permission>  permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){

        List<SimpleGrantedAuthority> authorities  = getPermissions()
                .stream()
                .map(i -> new SimpleGrantedAuthority(i.getPermission()))
                .collect(Collectors.toList());
        //authorities.add(new SimpleGrantedAuthority("ROLE_"+name()));
        return authorities;

    }
}
