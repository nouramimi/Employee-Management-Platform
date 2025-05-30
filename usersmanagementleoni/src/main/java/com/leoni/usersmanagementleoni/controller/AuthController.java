package com.leoni.usersmanagementleoni.controller;

import com.leoni.usersmanagementleoni.dto.AuthResponseDTO;
import com.leoni.usersmanagementleoni.dto.LoginDto;
import com.leoni.usersmanagementleoni.dto.RegisterDto;
import com.leoni.usersmanagementleoni.input.EmployeeWorkingData;
import com.leoni.usersmanagementleoni.input.ExcelReader;
import com.leoni.usersmanagementleoni.input.HibernateUtil;
import com.leoni.usersmanagementleoni.model.Role;
import com.leoni.usersmanagementleoni.model.UserEntity;
import com.leoni.usersmanagementleoni.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.leoni.usersmanagementleoni.repository.RoleRepository;
import com.leoni.usersmanagementleoni.repository.UserRepository;
import com.leoni.usersmanagementleoni.security.JwtGenerator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    private final EmployeeService employeeService;

    private final Environment env;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator,
                          Environment env, EmployeeService employeeService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.env = env;
        this.employeeService = employeeService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<Map<String, String>> register(@RequestBody RegisterDto registerDto) {
        Map<String, String> response = new HashMap<>();
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            response.put("message", "Username is taken!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
        user.setEmail(registerDto.getEmail());
        Role roles = roleRepository.findByName("USER").get();
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);

        response.put("message", "User registered success!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*@GetMapping("/home")
    public ResponseEntity<String> homePage() {
        return new ResponseEntity<>("Welcome to the home page!", HttpStatus.OK);
    }*/
    @GetMapping("/home")
    public ResponseEntity<String> homePage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>("Welcome to the home page!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

private static final String UPLOADED_FOLDER = "C:/absolute/path/to/uploads/";


   @PostMapping("/upload")
   public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("files") MultipartFile[] files) {
       Map<String, String> response = new HashMap<>();
       String uploadDirectory = "C:/absolute/path/to/uploads/";

       // Ensure the uploads directory exists
       File uploadDir = new File(uploadDirectory);
       if (!uploadDir.exists()) {
           uploadDir.mkdirs();
       }

       for (MultipartFile file : files) {
           try {
               if (!file.getOriginalFilename().endsWith(".xlsx")) {
                   response.put("message", "Invalid file type. Only .xlsx files are allowed.");
                   return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
               }
               Path path = Paths.get(uploadDirectory + file.getOriginalFilename());
               Files.write(path, file.getBytes());

               // Process the file
               List<EmployeeWorkingData> employeeList = employeeService.readAndProcessExcelFile(path.toString());
               employeeService.saveEmployeeData(employeeList);

               response.put("message", "File uploaded and processed successfully!");
           } catch (IOException e) {
               e.printStackTrace();
               response.put("message", "File upload failed!");
               return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
           }
       }

       return new ResponseEntity<>(response, HttpStatus.OK);
   }


}
