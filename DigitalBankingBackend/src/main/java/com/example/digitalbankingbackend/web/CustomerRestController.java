package com.example.digitalbankingbackend.web;

import com.example.digitalbankingbackend.DTOs.CustomerDTO;
import com.example.digitalbankingbackend.entities.Customer;
import com.example.digitalbankingbackend.exceptions.CustomerNotFoundException;
import com.example.digitalbankingbackend.services.BankAccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccServ;

    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccServ.listCustomers();
    }

    @GetMapping("/customers/search")
    public List<CustomerDTO> searchCustomers(
            @RequestParam(name = "keyword" , defaultValue = "") String keyword
    ){
        return bankAccServ.searchCustomers("%"+keyword+"%");
    }

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name = "id") Long custId) throws CustomerNotFoundException {
        return bankAccServ.getCustomer(custId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        return bankAccServ.saveCustomer(customerDTO);
    }

    @PutMapping("/customers/{custId}")
    public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO,@PathVariable Long custId){
        customerDTO.setId(custId);
        return bankAccServ.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id){
        bankAccServ.deleteCustomer(id);
    }

}
