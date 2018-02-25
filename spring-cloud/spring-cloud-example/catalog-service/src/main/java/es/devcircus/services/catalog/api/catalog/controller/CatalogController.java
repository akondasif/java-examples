package es.devcircus.services.catalog.api.catalog.controller;

import es.devcircus.services.catalog.api.catalog.dto.Catalog;
import es.devcircus.services.catalog.gateway.catalog.CatalogGateway;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Adrian Novegil <adrian.novegil@gmail.com>
 */
@RestController
@RequestMapping("/v1")
public class CatalogController {

    private final CatalogGateway catalogDao;

    /**
     *
     * @param catalogDao
     */
    @Autowired
    public CatalogController(CatalogGateway catalogDao) {
        this.catalogDao = catalogDao;
    }

    /**
     *
     * @return
     */
    @RequestMapping(path = "/catalog", method = RequestMethod.GET, name = "getCatalog")
    public ResponseEntity<Catalog> getCatalog() {
        // Return the data.
        return Optional.ofNullable(catalogDao.getCatalog())
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
