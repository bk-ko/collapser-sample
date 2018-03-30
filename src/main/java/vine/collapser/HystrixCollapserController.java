package vine.collapser;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vine.collapser.domain.Product;

import java.util.concurrent.ExecutionException;

@RestController
class HystrixCollapserController {

    private CollapserService collapserService;

    public HystrixCollapserController(CollapserService collapserService) {
        this.collapserService = collapserService;
    }

    @RequestMapping(value = "/collapser/{id}", method = RequestMethod.GET, produces = "application/json")
    public Product getProduct(@PathVariable long id) throws ExecutionException, InterruptedException {

        Product product = collapserService.getProductById(id);
        Assert.notNull(product, "Error : Product is null");

        return product;
    }

}