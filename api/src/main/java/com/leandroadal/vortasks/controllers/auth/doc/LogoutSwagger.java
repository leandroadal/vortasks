package com.leandroadal.vortasks.controllers.auth.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Logout do usuário", description = "Desloga um usuário utilizando o token de autenticação")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Logout realizado com sucesso"),
    // ------- ERROS --------
    @ApiResponse(responseCode = "401", description = "Token de autenticação inválido", 
            content = @Content(schema = @Schema(implementation = StandardError.class))),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", 
            content = @Content(schema = @Schema(implementation = StandardError.class)))
})
@SecurityRequirement(name = "bearerAuth")
public @interface LogoutSwagger {
    
}
