package ge.ibsu.demo.entities.enums;

public enum Permission {
    CUSTOMER_READ("customer:read"),CUSTOMER_ADD("customer:add");

    Permission(String permission) {
        this.permission = permission;
    }

    private String permission;

    public String getPermission() {
        return permission;
    }
}
