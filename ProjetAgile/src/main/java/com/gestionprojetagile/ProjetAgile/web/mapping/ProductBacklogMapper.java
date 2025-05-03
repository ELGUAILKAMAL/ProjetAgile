package com.gestionprojetagile.ProjetAgile.web.mapping;


import com.gestionprojetagile.ProjetAgile.web.DTO.ProductBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductBacklogMapper {
    ProductBacklogDTO productBlToProductBlDto(ProductBacklog productBacklog);

    ProductBacklog productBlDtoToProductBl(ProductBacklogDTO ProductBacklogDTO);
}
