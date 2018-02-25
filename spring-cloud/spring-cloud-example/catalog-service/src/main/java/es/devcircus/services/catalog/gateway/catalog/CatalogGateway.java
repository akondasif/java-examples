package es.devcircus.services.catalog.gateway.catalog;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import es.devcircus.services.catalog.api.catalog.dto.Catalog;
import es.devcircus.services.catalog.api.product.dto.Product;
import java.util.HashSet;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@Service
public class CatalogGateway {

    /**
     *
     * @return
     */
    @HystrixCommand
    public Catalog getCatalog() {
        // Define mock data
        Catalog catalog;
        // Mock data
        Product p1 = new Product("Name 1", "Identify 1", 2.0);
        Product p2 = new Product("Name 2", "Identify 2", 2.0);
        Product p3 = new Product("Name 3", "Identify 3", 2.0);
        Product p4 = new Product("Name 4", "Identify 4", 2.0);
        Product p5 = new Product("Name 5", "Identify 5", 2.0);
        HashSet list = new HashSet<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);        
        catalog = new Catalog("Test Catalog.");
        catalog.setProducts(list);
        // Return the data.
        return catalog;
    }

}
