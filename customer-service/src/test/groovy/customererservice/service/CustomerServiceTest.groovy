package customererservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.nab.icommerce.customerservice.entity.Customer
import com.nab.icommerce.customerservice.model.CustomerRequest
import com.nab.icommerce.customerservice.model.CustomerResponse
import com.nab.icommerce.customerservice.repository.CustomerRepository
import com.nab.icommerce.customerservice.service.CustomerService
import customererservice.utils.TestUtils
import spock.lang.Specification

class CustomerServiceTest extends Specification {

    private CustomerService customerService
    private CustomerRepository customerRepository = Mock()
    private ObjectMapper objectMapper = new ObjectMapper()
    def setup() {
        customerService = new CustomerService(customerRepository)
    }

    def "test regis new customer"() {
        given:
        CustomerRequest request = objectMapper
                .readValue(TestUtils.toString("json/regis-customer.json"), new TypeReference<CustomerRequest>(){})

        customerRepository.findByIdOrEmailOrMobile(_,_,_) >> Collections.EMPTY_LIST

        customerRepository.save(_) >> Customer.builder().address("HCM City")
                .email("test@gmail.com")
                .mobile("+8490999999").build();
        when:
        CustomerResponse.CustomerItem result = customerService.registNewCustomer(request)
        then:
        result != null
        result.getAddress() == "HCM City"
        result.getMobile() == "+8490999999"
    }

    def "test get customer info"() {
        given:
        customerRepository.findByIdOrEmailOrMobile(_,_,_) >> List.of(Customer.builder()
                .mobile("+8490999999")
                .email("test@gmail.com")
                .address("HCM City")
                .build()
        )
        when:
        CustomerResponse response = customerService.getCustomers(null, "test@gmail.com", "+8490999999")
        then:
        response != null
        response.getData() != null
        response.getData().get(0).getMobile() == "+8490999999"
        response.getData().get(0).getAddress() == "HCM City"
    }
}
