package br.com.brigadadoslobos.gerenciador.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    @Autowired
    private Environment env;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String origin = request.getHeader("Origin");

        // ========================================
        // CONFIGURAÇÃO BASEADA NO AMBIENTE
        // ========================================
        if (Arrays.asList(env.getActiveProfiles()).contains("prod")) {
            // 🔐 PRODUÇÃO: Apenas origens específicas
            if (isAllowedOrigin(origin)) {
                response.setHeader("Access-Control-Allow-Origin", origin);
                response.setHeader("Access-Control-Allow-Credentials", "true");
            }
        } else {
            // 🔓 DESENVOLVIMENTO/TESTE: Permite qualquer origem
            response.setHeader("Access-Control-Allow-Origin", origin != null ? origin : "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
        }

        // Headers comuns para todos os ambientes
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, PATCH, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Authorization, Content-Type, Accept, X-Requested-With, Origin, " +
                        "Access-Control-Request-Method, Access-Control-Request-Headers");
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        // Responde imediatamente para requisições OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    /**
     * Verifica se a origem é permitida em PRODUÇÃO
     * Adicione aqui os domínios autorizados
     */
    private boolean isAllowedOrigin(String origin) {
        if (origin == null) {
            return false;
        }

        // 🔐 PRODUÇÃO: Domínios autorizados
        String[] allowedOrigins = {
                "https://gerenciadobdl243.netlify.app",
                "http://localhost:4200"                  // Para testar localmente
        };

        for (String allowed : allowedOrigins) {
            if (origin.equals(allowed) || origin.startsWith(allowed)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) {
        // Opcional: log de inicialização
        System.out.println("🌐 SimpleCorsFilter inicializado");
    }

    @Override
    public void destroy() {
        // Cleanup se necessário
    }
}