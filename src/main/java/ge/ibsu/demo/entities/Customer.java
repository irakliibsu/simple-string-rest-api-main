package ge.ibsu.demo.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @SequenceGenerator(name = "customer_customer_id_seq",sequenceName = "customer_customer_id_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="customer_customer_id_seq")
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastName;

    @Column (name = "create_date")
    private Date createDate;

    @Column (name = "active")
    private Integer active;

    @JoinColumn(name = "address_id")
    @ManyToOne(fetch = FetchType.LAZY )
    private Address address;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
