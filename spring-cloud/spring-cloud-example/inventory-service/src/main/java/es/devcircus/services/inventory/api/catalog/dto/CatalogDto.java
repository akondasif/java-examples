package es.devcircus.services.inventory.api.catalog.dto;

import es.devcircus.services.inventory.model.product.Product;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
public class CatalogDto {

    private Long id;
    private Long catalogNumber;
    private List<Product> products = new ArrayList<>();
    private String name;

    public CatalogDto() {
    }

    public CatalogDto(String name, Long catalogNumber) {
        this.name = name;
        this.catalogNumber = catalogNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber(Long catalogNumber) {
        this.catalogNumber = catalogNumber;
    }

    @Override
    public String toString() {
        return "Catalog{"
                + "id=" + id
                + ", catalogNumber=" + catalogNumber
                + ", products=" + products
                + ", name='" + name + '\''
                + '}';
    }
}
