package com.leandroadal.vortasks.controllers.social.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;
import com.leandroadal.vortasks.entities.social.tasks.dto.onlinemission.response.OnlineMissionResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OnlineMissionDocSwagger {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Buscar missão online por ID", description = "Busca uma missão online pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão online encontrada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "ID de missão inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface GetOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar todas as missões online", description = "Lista todas as missões online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missões online listadas com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface GetAllOnlineMissionsSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar missões online por página", description = "Lista missões online por página, com filtros opcionais de título, status, tipo e usuário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missões online listadas com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface FindPageOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Criar missão online", description = "Cria uma nova missão online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Missão online criada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de missão online inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface AddOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar missão online", description = "Edita uma missão online existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão online editada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de missão online inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface EditOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar parcialmente missão online", description = "Edita parcialmente uma missão online existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Missão online editada com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de missão online inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface PartialEditOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Excluir missão online", description = "Exclui uma missão online existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Missão online excluída com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de missão inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface DeleteOnlineMissionSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Adicionar requisitos à missão online", description = "Adiciona requisitos à missão online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requisitos adicionados com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de requisitos inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface AddToRequirementsSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar requisitos da missão online", description = "Edita os requisitos da missão online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Requisitos editados com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = OnlineMissionResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de requisitos inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online ou tarefa não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface EditRequirementsSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Remover requisito da missão online", description = "Remove um requisito da missão online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisito removido com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de missão ou tarefa inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online ou tarefa não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface RemoveFromRequirementsSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Limpar requisitos da missão online", description = "Limpa todos os requisitos da missão online")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Requisitos limpos com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de missão inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Missão online não encontrada",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface ClearRequirementsSwagger {
    }

}
