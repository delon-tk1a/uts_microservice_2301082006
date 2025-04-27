package com.teknologiinformasi.restapi.customer.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teknologiinformasi.restapi.customer.model.Customer;
import com.teknologiinformasi.restapi.customer.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

   @Autowired
   private CustomerService customerService;


   // Endpoint untuk mengambil semua customer
   @GetMapping
   public List<Customer> getAllCustomer() {
    log.info("GET /api/customer accessed");
       return customerService.getAllCustomers();
   }


   // Endpoint untuk mengambil Customer berdasarkan id
   @GetMapping("/{id}")
   public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
    log.info("GET /api/customer/{} accessed", id);
       return customerService.getCustomerById(id)
               .map(customer -> ResponseEntity.ok().body(customer))
               .orElse(ResponseEntity.notFound().build());
   }


   // Endpoint untuk membuat customer baru
   @PostMapping
   public Customer createCustomer(@RequestBody Customer customer) {
    log.info("POST /api/customer accessed with body: {}", customer);
       return customerService.createCustomer(customer);
   }


   // Endpoint untuk mengupdate customer yang sudah ada
   @PutMapping("/{id}")
   public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
    log.info("PUT /api/customer/{} accessed with body: {}", id, customerDetails);
          
    try {
           Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
           return ResponseEntity.ok(updatedCustomer);
       } catch (RuntimeException e) {
        log.warn("PUT /api/customer/{} failed: {}", id, e.getMessage());
           return ResponseEntity.notFound().build();
       }
   }


//     Endpoint untuk menghapus customer
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCustomer(@PathVariable Long id) {
        log.info("DELETE /api/customer/{} accessed", id);
        Map<String, String> response = new HashMap<>();
        try {
            customerService.deleteCustomer(id);
            response.put("message", "Customer berhasil dihapus");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            response.put("message", "Customer tidak ditemukan dengan id " + id);
            log.warn("DELETE /api/customer/{} failed: {}", id, e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
