package com.vpd.authorizationserver.impl;


import com.vpd.authorizationserver.dto.APIResponse;
import com.vpd.authorizationserver.dto.LoginDTO;
import com.vpd.authorizationserver.dto.SignupDTO;
import com.vpd.authorizationserver.vo.CustomerVO;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    APIResponse<CustomerVO> login(LoginDTO dto);

    APIResponse<CustomerVO> register(SignupDTO dto);

    APIResponse<CustomerVO> validateToken(String token);
}
