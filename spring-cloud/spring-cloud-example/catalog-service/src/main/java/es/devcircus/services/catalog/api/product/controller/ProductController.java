package es.devcircus.services.catalog.api.product.controller;

import es.devcircus.services.catalog.gateway.product.ProductGateway;
import es.devcircus.services.catalog.api.product.dto.Product;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@RestController
@RequestMapping("/v1")
public class ProductController {

    private final ProductGateway productDao;

    /**
     * 
     * @param productDao
     */
    @Autowired
    public ProductController(ProductGateway productDao) {
        this.productDao = productDao;
    }

    /**
     *
     * @param productId
     * @return
     */
    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET, name = "getProduct")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") String productId) {
        //Return the data.
        return Optional.ofNullable(productDao.getProduct(productId))
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
