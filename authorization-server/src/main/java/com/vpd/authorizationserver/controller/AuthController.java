package com.vpd.authorizationserver.controller;

import com.vpd.authorizationserver.dto.APIResponse;
import com.vpd.authorizationserver.dto.LoginDTO;
import com.vpd.authorizationserver.dto.SignupDTO;
import com.vpd.authorizationserver.impl.CustomerService;
import com.vpd.authorizationserver.vo.CustomerVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<APIResponse<CustomerVO>> login(@RequestBody LoginDTO dto){
        log.info("Login began {}",dto);
        APIResponse<CustomerVO> apiResponse = customerService.login(dto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
    @PostMapping("/register")
    public ResponseEntity<APIResponse<CustomerVO>> register(@Valid @RequestBody SignupDTO dto) {
        APIResponse<CustomerVO> apiResponse = customerService.register(dto);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping("/validateToken")
    public ResponseEntity<APIResponse<CustomerVO>> validateToken(@RequestParam String token){
        APIResponse<CustomerVO> apiResponse = customerService.validateToken(token);
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
