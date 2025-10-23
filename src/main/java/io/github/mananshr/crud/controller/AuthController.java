package io.github.mananshr.crud.controller;

import io.github.mananshr.crud.exception.ApiException;
import io.github.mananshr.crud.model.User;
import io.github.mananshr.crud.repository.UserRepository;
import io.micronaut.data.exceptions.EmptyResultException;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import jakarta.inject.Inject;
import java.util.Optional;

/**
 * AuthController handles user authentication requests.
 * - Sign up: Registers a new user with an email and password.
 * - Login: Authenticates a user and retrieves their information.
 */
@Controller("/auth")
@Secured(SecurityRule.IS_ANONYMOUS)
public class AuthController {
    
    @Inject
    private UserRepository userRepository;

    // Logger for this controller - use Logger to produce colored, centralized logs
    private static final io.github.mananshr.crud.util.Logger.LoggerInstance LOG = io.github.mananshr.crud.util.Logger.getLogger(AuthController.class);

    /**
     * Sign up a new user.
     * - Validates basic input
     * - Ensures email is unique
     * - Persists user to MongoDB
     */
    @Post("/signup")
    public HttpResponse<User> signup(@Body User user) {
        LOG.info("Signup request received for email: %s", user != null ? user.getEmail() : "null");

        if (user == null) {
            LOG.warn("Signup failed: request body is null");
            throw new ApiException("Invalid request", HttpStatus.BAD_REQUEST);
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            LOG.warn("Signup failed: missing email");
            throw new ApiException("Email is required", HttpStatus.BAD_REQUEST);
        }
        
        try {
            User existingUser = userRepository.findByEmail(user.getEmail());
            if (existingUser != null) {
                LOG.warn("Signup failed: email already exists: %s", user.getEmail());
                throw new ApiException("Email already exists", HttpStatus.BAD_REQUEST);
            }
        } catch (EmptyResultException e) {
            // Expected when user not found; proceed with signup
            LOG.debug("No existing user found for email %s, proceeding to create", user.getEmail());
        } catch (Exception e) {
            // Unexpected repository error
            LOG.error("Error checking existing user for email %s: %s", user.getEmail(), e.getMessage());
            throw new ApiException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        User savedUser = userRepository.save(user);
        LOG.success("User created with id: %s email: %s", savedUser.getId(), savedUser.getEmail());
        return HttpResponse.created(savedUser);
    }

    /**
     * Login endpoint (placeholder).
     * Currently returns the user record if present.
     * Authentication flow should be handled by Auth0 / Micronaut security in production.
     */
    @Post("/login")
    public User login(@Body LoginRequest request) {
        LOG.info("Login attempt for email: %s", request != null ? request.getEmail() : "null");

        if (request == null || request.getEmail() == null || request.getEmail().isEmpty()) {
            LOG.warn("Login failed: missing email in request");
            throw new ApiException("Email is required", HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userRepository.findByEmail(request.getEmail());
            if (user == null) {
                LOG.warn("Login failed: user not found for email %s", request.getEmail());
                throw new ApiException("User not found", HttpStatus.NOT_FOUND);
            }
            LOG.success("Login lookup successful for email: %s", request.getEmail());
            return user;
        } catch (EmptyResultException e) {
            LOG.warn("Login failed: repository returned empty result for email %s", request.getEmail());
            throw new ApiException("User not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LOG.error("Unexpected error during login for email %s: %s", request.getEmail(), e.getMessage());
            throw new ApiException("Internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
