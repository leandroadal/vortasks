package com.leandroadal.vortasks.controllers.auth.doc;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;
import com.leandroadal.vortasks.entities.user.dto.LoginResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Autenticação do usuário", description = "Autentica um usuário com username e senha")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Token de autenticação gerado com sucesso", content = { @Content(mediaType = "application/json", 
    schema = @Schema(implementation = LoginResponseDTO.class)) }),
    // ------- ERROS --------
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
})
public @interface LoginSwagger {
    
}
