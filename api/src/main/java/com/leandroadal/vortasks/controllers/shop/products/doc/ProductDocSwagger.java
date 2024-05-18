package com.leandroadal.vortasks.controllers.shop.products.doc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.leandroadal.vortasks.controllers.exceptions.StandardError;
import com.leandroadal.vortasks.entities.shop.dto.ProductResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductDocSwagger {

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Criar produto", description = "Cria um novo produto na loja")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de produto inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface CreateProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar todos os produtos", description = "Lista todos os produtos disponíveis na loja")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface GetAllProductsSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Buscar produto por ID", description = "Busca um produto pelo seu ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "ID de produto inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @interface GetProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Listar produtos por página", description = "Lista produtos por página, com filtros opcionais de nome e categoria")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produtos listados com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Parâmetros de paginação inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface FindPageProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar produto", description = "Edita um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto editado com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de produto inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface EditProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Editar parcialmente produto", description = "Edita parcialmente um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto editado com sucesso", content = { @Content(mediaType = "application/json", 
        schema = @Schema(implementation = ProductResponseDTO.class)) }),
        @ApiResponse(responseCode = "400", description = "Dados de produto inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface PartialEditProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Excluir produto", description = "Exclui um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto excluído com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de produto inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface DeleteProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Desativar produto", description = "Desativa um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Produto desativado com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de produto inválido",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface DesativeProductSwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Atualizar categorias de um produto", description = "Atualiza as categorias de um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categorias do produto atualizadas com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de produto ou IDs de categoria inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface UpdateProductCategorySwagger {
    }

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Operation(summary = "Remover categorias de um produto", description = "Remove categorias de um produto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Categorias do produto removidas com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID de produto ou IDs de categoria inválidos",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "403", description = "Acesso negado", 
                content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado",
                content = {@Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class))}),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                content = @Content(mediaType = "application/json", schema = @Schema(implementation = StandardError.class)))
    })
    @SecurityRequirement(name = "bearerAuth")
    @interface DeleteProductCategorySwagger {
    }

}
