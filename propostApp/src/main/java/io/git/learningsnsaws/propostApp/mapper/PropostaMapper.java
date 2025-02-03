package io.git.learningsnsaws.propostApp.mapper;

import io.git.learningsnsaws.propostApp.dto.PropostaRequestDto;
import io.git.learningsnsaws.propostApp.dto.PropostaResponseDto;
import io.git.learningsnsaws.propostApp.entity.Proposta;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.text.NumberFormat;
import java.util.List;

@Mapper
public interface PropostaMapper {

    PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class);

    @Mapping(target = "usuario.nome", source = "nome")
    @Mapping(target = "usuario.sobrenome", source = "sobrenome")
    @Mapping(target = "usuario.telefone", source = "telefone")
    @Mapping(target = "usuario.cpf", source = "cpf")
    @Mapping(target = "usuario.renda", source = "renda")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aprovada", ignore = true)
    @Mapping(target = "integrada", constant = "true")
    @Mapping(target = "observacao", ignore = true)
    Proposta convertDtoToProposta(PropostaRequestDto propostaRequestDto);

    @Mapping(target = "nome", source = "usuario.nome")
    @Mapping(target = "sobrenome", source = "usuario.sobrenome")
    @Mapping(target = "telefone", source = "usuario.telefone")
    @Mapping(target = "cpf", source = "usuario.cpf")
    @Mapping(target = "renda", source = "usuario.renda")
    @Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFormatado(proposta))")
    PropostaResponseDto convertEntityToDto(Proposta proposta);

    List<PropostaResponseDto> convertListEntityToListDto(List<Proposta> propostas);

    default String setValorSolicitadoFormatado(Proposta proposta) {
        return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
    }
}
