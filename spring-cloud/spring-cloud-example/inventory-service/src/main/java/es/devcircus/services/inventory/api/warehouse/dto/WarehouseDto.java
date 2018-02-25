package es.devcircus.services.inventory.api.warehouse.dto;

import es.devcircus.services.inventory.model.address.Address;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class WarehouseDto {

    private Long id;
    private String name;
    private Address address;

    public WarehouseDto() {
    }

    public WarehouseDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Warehouse{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address=" + address
                + '}';
    }
}
