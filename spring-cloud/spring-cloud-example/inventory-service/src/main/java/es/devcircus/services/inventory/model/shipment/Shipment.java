package es.devcircus.services.inventory.model.shipment;

import es.devcircus.services.inventory.model.address.Address;
import es.devcircus.services.inventory.model.inventory.Inventory;
import es.devcircus.services.inventory.model.warehouse.Warehouse;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class Shipment {

    private Long id;
    private Set<Inventory> inventories = new HashSet<>();
    private Address deliveryAddress;
    private Warehouse fromWarehouse;
    private ShipmentStatus shipmentStatus;

    public Shipment() {
    }

    public Shipment(Set<Inventory> inventories, Address deliveryAddress, Warehouse fromWarehouse, ShipmentStatus shipmentStatus) {
        this.inventories = inventories;
        this.deliveryAddress = deliveryAddress;
        this.fromWarehouse = fromWarehouse;
        this.shipmentStatus = shipmentStatus;
    }

    public Long getId() {
        return id;
    }

    public Set<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(Set<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Warehouse getFromWarehouse() {
        return fromWarehouse;
    }

    public void setFromWarehouse(Warehouse fromWarehouse) {
        this.fromWarehouse = fromWarehouse;
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    @Override
    public String toString() {
        return "Shipment{"
                + "id=" + id
                + ", inventories=" + inventories
                + ", deliveryAddress=" + deliveryAddress
                + ", fromWarehouse=" + fromWarehouse
                + ", shipmentStatus=" + shipmentStatus
                + '}';
    }
}
