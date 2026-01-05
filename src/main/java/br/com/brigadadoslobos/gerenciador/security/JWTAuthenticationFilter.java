package br.com.brigadadoslobos.gerenciador.security;

import br.com.brigadadoslobos.gerenciador.domains.dtos.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            CredentialsDTO creds = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>());
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            return authentication;
        } catch (IOException e) {
            // Captura apenas exceções de I/O ao ler o request
            throw new RuntimeException(e);
        }
        // AuthenticationException (incluindo DisabledException) será propagada automaticamente
    }

    /*
        @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                FilterChain chain, Authentication authResult)
                throws IOException, ServletException {
            String username = ((UserSS) authResult.getPrincipal()).getUsername();
            String token = jwtUtil.generateToken(username);
            response.setHeader("access-control-expose-headers", "Authorization");
            response.setHeader("Authorization", "Bearer " + token);
        }
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        UserSS user = (UserSS) authResult.getPrincipal();
        String username = user.getUsername();
        List<String> roles = user.getAuthorities().stream()
                .map(ga -> ga.getAuthority())
                .collect(Collectors.toList());

        String token = jwtUtil.generateToken(username, roles);

        response.setHeader("access-control-expose-headers", "Authorization");
        response.setHeader("Authorization", "Bearer " + token);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // agora inclui também o id da sede
        String body = new ObjectMapper().writeValueAsString(
                java.util.Map.of(
                        "token", "Bearer " + token,
                        "roles", roles,
                        "email", username,
                        "userId", user.getId(),
                        "headQuarterId", user.getHeadQuarterId()
                )
        );
        response.getWriter().write(body);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String message = "Email ou senha inválidos";

        // Verifica o tipo específico de exceção
        if (failed instanceof org.springframework.security.authentication.DisabledException) {
            message = "Usuário desligado. Entre em contato com o administrador";
        } else if (failed instanceof org.springframework.security.authentication.BadCredentialsException) {
            message = "Email ou senha inválidos";
        }

        response.getWriter().append(json(message));
    }

    private String json(String message) {
        long date = new Date().getTime();
        return "{"
                + "\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Não autorizado\", "
                + "\"message\": \"" + message + "\", "
                + "\"path\": \"/login\"}";
    }
}
