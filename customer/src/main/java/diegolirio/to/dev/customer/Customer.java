package diegolirio.to.dev.customer;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("customers")
public class Customer {

    @Id
    private String id;
    private String name;
}
