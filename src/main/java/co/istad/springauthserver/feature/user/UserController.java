package co.istad.springauthserver.feature.user;

import co.istad.springauthserver.feature.user.dto.UserRequest;
import co.istad.springauthserver.feature.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }

    @PutMapping("/{uuid}/enable")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void enableUser(@PathVariable String uuid) {
        userService.enableUser(uuid);
    }

    @PutMapping("/{uuid}/diable")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void diableUser(@PathVariable String uuid) {
        userService.disableUser(uuid);
    }
}
