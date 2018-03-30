package vine.collapser;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vine.collapser.domain.Product;
import vine.collapser.domain.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL;

@Service
public class CollapserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final ProductRepository productRepository;

    public CollapserService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @HystrixCollapser(scope = GLOBAL,
                      batchMethod = "getProductByIds",
                      collapserProperties = {
                          @HystrixProperty(name = "timerDelayInMilliseconds", value = "3000"), //default 10ms
                          @HystrixProperty(name = "maxRequestsInBatch", value = "10")
                      })
    public Product getProductById(Long id) {
        throw new RuntimeException("This method body should not be executed");
    }

    @HystrixCommand
    public List<Product> getProductByIds(List<Long> ids) {

        log.info("## Batched calls with IDs " + Objects.toString(ids));

        Map<Long, Product> productMap =
            productRepository
                .findAllById(ids)
                .stream()
                .collect(
                    Collectors.toMap(Product::getPrdNo, Function.identity()));

        // todo : order of result should match ids !!
        List<Product> products = ids.stream()
                                    .map(productMap::get)
                                    .collect(Collectors.toList());

        // just add "QueriedWith" field
        products.stream()
                .filter(Objects::nonNull)
                .forEach(p -> p.setQueriedWith(ids.toString()));
        return products;
    }

}