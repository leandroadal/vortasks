package com.leandroadal.vortasks.controllers.social.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;
import com.leandroadal.vortasks.entities.social.tasks.dto.grouptask.GroupTaskResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GroupTaskDocSwagger {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Buscar tarefa em grupo por ID", description = "Busca uma tarefa em grupo pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa em grupo encontrada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = GroupTaskResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "ID de tarefa inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface GetGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar tarefas em grupo", description = "Lista todas as tarefas em grupo, com filtros opcionais de nome, status e tipo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefas em grupo listadas com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = GroupTaskResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface FindPageGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Criar tarefa em grupo", description = "Cria uma nova tarefa em grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa em grupo criada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = GroupTaskResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de tarefa em grupo inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface AddGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar tarefa em grupo", description = "Edita uma tarefa em grupo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa em grupo editada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = GroupTaskResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de tarefa em grupo inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface EditGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar parcialmente tarefa em grupo", description = "Edita parcialmente uma tarefa em grupo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tarefa em grupo editada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = GroupTaskResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de tarefa em grupo inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface PartialEditGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Excluir tarefa em grupo", description = "Exclui uma tarefa em grupo existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Tarefa em grupo excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de tarefa inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface DeleteGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Adicionar usuário à tarefa em grupo", description = "Adiciona um usuário à tarefa em grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário adicionado com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de tarefa ou nome de usuário inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo ou usuário não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "409", description = "Número máximo de participantes atingido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface AddUserToGroupTaskSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Remover usuário da tarefa em grupo", description = "Remove um usuário da tarefa em grupo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de tarefa ou nome de usuário inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Tarefa em grupo ou usuário não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "409", description = "Número mínimo de participantes não pode ser atingido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface RemoveUserFromGroupTaskSwagger {
    }

}
