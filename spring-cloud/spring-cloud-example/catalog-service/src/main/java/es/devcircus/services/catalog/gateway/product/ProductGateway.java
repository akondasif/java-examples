package es.devcircus.services.catalog.gateway.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import es.devcircus.services.catalog.api.product.dto.Product;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@Service
public class ProductGateway {

    /**
     * 
     * @param productId
     * @return 
     */
    @HystrixCommand
    public Product getProduct(String productId) {
        // Mock data
        Product p1 = new Product("Name 1", "Identify 1", 2.0);
        // Return the data
        return p1;
    }
}
