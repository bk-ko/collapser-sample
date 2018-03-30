package vine.collapser.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private long prdNo;

    private String name;

    private String queriedWith;

    public Product() {
    }

    public Product(long prdNo, String name, String queriedWith) {
        this.prdNo = prdNo;
        this.name = name;
        this.queriedWith = queriedWith;
    }

    public long getPrdNo() {
        return prdNo;
    }

    public String getName() {
        return name;
    }

    public String getQueriedWith() {
        return queriedWith;
    }

    public void setQueriedWith(String queriedWith) {
        this.queriedWith = queriedWith;
    }
}
