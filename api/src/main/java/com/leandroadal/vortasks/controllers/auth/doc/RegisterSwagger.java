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

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Registro de um novo usuário", description = "Registra um novo usuário com os dados fornecidos")
@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
    // ------- ERROS --------
    @ApiResponse(responseCode = "400", description = "Dados de registro inválidos", 
            content = @Content(schema = @Schema(implementation = StandardError.class))),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", 
            content = @Content(schema = @Schema(implementation = StandardError.class)))
})
public @interface RegisterSwagger {
    
}