package diegolirio.to.dev.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @PostMapping
    public Customer save(@RequestBody Customer customer) {
        return this.customerRepository.save(customer);
    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable String id) {
        return this.customerRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public Iterable<Customer> getAll() {
        return this.customerRepository.findAll();
    }
}
