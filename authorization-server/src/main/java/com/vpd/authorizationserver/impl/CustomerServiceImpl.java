package com.vpd.authorizationserver.impl;

import com.vpd.authorizationserver.config.security.JwtService;
import com.vpd.authorizationserver.dto.APIResponse;
import com.vpd.authorizationserver.dto.LoginDTO;
import com.vpd.authorizationserver.dto.SignupDTO;
import com.vpd.authorizationserver.entity.Customer;
import com.vpd.authorizationserver.exceptions.APIException;
import com.vpd.authorizationserver.repository.CustomerRepository;
import com.vpd.authorizationserver.vo.CustomerVO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public APIResponse<CustomerVO> register(SignupDTO dto) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(dto.getEmail());
        if (customerOptional.isPresent()) {
            throw APIException.builder()
                    .message("Customer already exists")
                    .statusCode(409)
                    .build();
        }
        Customer newCustomer = mapper.map(dto, Customer.class);
        String hashedPassword = passwordEncoder.encode(dto.getPassword());
        newCustomer.setPassword(hashedPassword);
        Customer savedCustomer = customerRepository.save(newCustomer);
        CustomerVO customerVO = mapper.map(savedCustomer, CustomerVO.class);
        customerVO.setCreatedAt(savedCustomer.getCreatedAt());
        customerVO.setUpdatedAt(savedCustomer.getUpdatedAt());
        APIResponse<CustomerVO> apiResponse = new APIResponse<>();
        apiResponse.setMessage("Customer signed up successfully");
        apiResponse.setStatus(201);
        apiResponse.setData(customerVO);
        return apiResponse;

    }

    @Override
    public APIResponse<CustomerVO> validateToken(String token) {
        if(jwtService.validateToken(token)){
            String userEmail = jwtService.extractUsernameFromToken(token);
            Customer customer = customerRepository.findCustomerByEmail(userEmail).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
            CustomerVO customerVO = mapper.map(customer, CustomerVO.class);
            customerVO.setCreatedAt(customer.getCreatedAt());
            customerVO.setUpdatedAt(customer.getUpdatedAt());
            APIResponse<CustomerVO> response = new APIResponse<>();
            response.setStatus(200);
            response.setMessage("Token is valid");
            response.setData(customerVO);
            return response;
        }
        throw APIException.builder()
                .statusCode(401)
                .message("Invalid token")
                .build();
    }

    @Override
    public APIResponse<CustomerVO> login(LoginDTO dto) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(dto.getEmail());
        if (customerOptional.isEmpty()) {
            throw APIException.builder()
                    .statusCode(401)
                    .message("Account doesn't exist")
                    .build();
        }
        Customer customer = customerOptional.get();
        if (!passwordEncoder.matches(dto.getPassword(), customer.getPassword())) {
            throw APIException.builder()
                    .statusCode(401)
                    .message("Invalid credentials")
                    .build();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        CustomerVO customerVO = mapper.map(customer, CustomerVO.class);
        var jwtToken = jwtService.generateToken(customer);
        customerVO.setToken(jwtToken);
        APIResponse<CustomerVO> response = new APIResponse<>();
        response.setStatus(200);
        response.setMessage("Login successful");
        response.setData(customerVO);
        log.info("Login details {}", response);
        return response;
    }
}

