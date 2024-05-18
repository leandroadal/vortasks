package com.leandroadal.vortasks.controllers.user.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;
import com.leandroadal.vortasks.entities.user.dto.UserResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserSwagger {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar todos os usuários", description = "Lista todos os usuários cadastrados no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = UserResponseDTO.class)) }),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface FindAllUsersSwagger {
    }

}
