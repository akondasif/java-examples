package es.devcircus.services.inventory.api.inventory.dto;

import es.devcircus.services.inventory.model.inventory.*;
import es.devcircus.services.inventory.model.product.Product;
import es.devcircus.services.inventory.model.warehouse.Warehouse;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class InventoryDto {

    private Long id;
    private String inventoryNumber;
    private Product product;
    private Warehouse warehouse;
    private InventoryStatus status;

    public InventoryDto() {
    }

    public InventoryDto(String inventoryNumber, Product product) {
        this.inventoryNumber = inventoryNumber;
        this.product = product;
    }

    public InventoryDto(String inventoryNumber, Product product, Warehouse warehouse, InventoryStatus status) {
        this.inventoryNumber = inventoryNumber;
        this.product = product;
        this.warehouse = warehouse;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public InventoryStatus getStatus() {
        return status;
    }

    public void setStatus(InventoryStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Inventory{"
                + "id=" + id
                + ", inventoryNumber='" + inventoryNumber + '\''
                + ", product=" + product
                + ", warehouse=" + warehouse
                + ", status=" + status
                + '}';
    }
}
