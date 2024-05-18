package com.leandroadal.vortasks.controllers.doc;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Requisição inválida", 
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))),
    @ApiResponse(responseCode = "403", description = "Acesso negado", 
    content = @Content),
    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", 
    content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
})
public @interface DefaultDocSwagger {

}
