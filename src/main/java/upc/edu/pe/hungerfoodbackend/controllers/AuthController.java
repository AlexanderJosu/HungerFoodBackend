package upc.edu.pe.hungerfoodbackend.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import upc.edu.pe.hungerfoodbackend.dtos.UserDTO;
import upc.edu.pe.hungerfoodbackend.dtos.request.LoginUserRequestDTO;
import upc.edu.pe.hungerfoodbackend.dtos.request.RegisterUserRequestDTO;
import upc.edu.pe.hungerfoodbackend.dtos.response.JwtResponse;
import upc.edu.pe.hungerfoodbackend.entities.User;
import upc.edu.pe.hungerfoodbackend.iservices.IUserService;
import upc.edu.pe.hungerfoodbackend.util.DTOConverter;

//Registrar y loguear usuarios
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private IUserService userService;
    @Autowired
    private DTOConverter dtoConverter;
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody RegisterUserRequestDTO request) {
        User user = dtoConverter.convertToEntity(request, User.class);
        user = userService.insert(user);
        UserDTO userDTO = dtoConverter.convertToDto(user, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequestDTO request) {
        JwtResponse jwtResponse = userService.login(request.getEmail(), request.getPassword());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", jwtResponse.getJwttoken());
        return ResponseEntity.ok().headers(responseHeaders).body(jwtResponse);
    }
}
