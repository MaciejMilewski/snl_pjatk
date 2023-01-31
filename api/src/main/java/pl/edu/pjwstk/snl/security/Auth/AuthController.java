package pl.edu.pjwstk.snl.security.Auth;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.snl.security.Jwt.JwtUtils;
import pl.edu.pjwstk.snl.security.Payload.Request.LoginRequest;
import pl.edu.pjwstk.snl.security.Payload.Request.SignupRequest;
import pl.edu.pjwstk.snl.security.Payload.Response.JwtResponse;
import pl.edu.pjwstk.snl.security.Payload.Response.MessageResponse;
import pl.edu.pjwstk.snl.security.Role.ERole;
import pl.edu.pjwstk.snl.security.Role.Role;
import pl.edu.pjwstk.snl.security.Role.RoleRepository;
import pl.edu.pjwstk.snl.security.User.Trainer.TrainerDTO;
import pl.edu.pjwstk.snl.security.User.User;
import pl.edu.pjwstk.snl.security.User.UserDTO;
import pl.edu.pjwstk.snl.security.User.UserDetailsImpl;
import pl.edu.pjwstk.snl.security.User.UserService;
import pl.edu.pjwstk.snl.team.Team;
import pl.edu.pjwstk.snl.team.TeamService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    final
    AuthenticationManager authenticationManager;

    final
    UserService userService;

    final
    RoleRepository roleRepository;

    final
    PasswordEncoder encoder;

    final
    JwtUtils jwtUtils;

    final TeamService teamService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        Long teamId = null;
        for (String role : roles) {
            if (role.equals(ERole.ROLE_TRAINER.name())) {
                User trainer = userService.findById(userDetails.getId()).orElse(null);
                Team team = trainer.getTeam();
                if (team != null) {
                    teamId = team.getId();
                }
            }
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                teamId));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }
        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        this.userService.setRolesFromStringSet(strRoles, roles);
        user.setRoles(roles);
        userService.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(
            @PathVariable("id") Long id,
            @RequestBody final UserDTO newUserData
            ) {
        if (userService.existsById(id)) {
            User user = this.userService.findById(id).orElse(null);

            Set<Role> roles = new HashSet<>();
            Set<String> strRoles = new HashSet<>(newUserData.getRoles());

            this.userService.setRolesFromStringSet(strRoles, roles);

            user.setUsername(newUserData.getUsername());
            user.setEmail(newUserData.getEmail());
            user.setPassword(encoder.encode(newUserData.getPassword()));
            user.setRoles(roles);
            userService.save(user);

            return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/trainers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<TrainerDTO>> getAllTrainers() {
        try {
            if (CollectionUtils.isNotEmpty(userService.findAll())) {
                return new ResponseEntity<>(userService.findAllTrainers(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(
            @PathVariable("id") Long id
    ) {
        try {
            if (this.userService.existsById(id)) {
                return new ResponseEntity<>(userService.findById(id).orElse(null), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            if (CollectionUtils.isNotEmpty(userService.findAll())) {
                return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/trainers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> assignTeamToTrainer(
            @PathVariable(value = "id") long trainerId,
            @RequestParam final long teamId
    ) {
        if (userService.existsById(trainerId)) {
            User user = userService.findById(trainerId).orElse(null);

            if (teamService.existsById(teamId)) {
                for (Role role : user.getRoles()) {
                    if (role.getName().equals(ERole.ROLE_TRAINER)) {
                        user.setTeam(teamService.findById(teamId).orElse(null));
                    }
                }

                userService.save(user);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/trainers/{id}/team")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Team> getTeamByTrainerId(@PathVariable(value = "id") long trainerId) {
        try {
            if (userService.existsById(trainerId)) {
                User user = userService.findById(trainerId).orElse(null);

                if (user.getTeam() != null) {
                    return new ResponseEntity<>(user.getTeam(), HttpStatus.OK);
                }

                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
