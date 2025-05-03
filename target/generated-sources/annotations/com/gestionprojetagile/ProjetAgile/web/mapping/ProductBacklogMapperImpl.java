package com.gestionprojetagile.ProjetAgile.web.mapping;

import com.gestionprojetagile.ProjetAgile.web.DTO.ProductBacklogDTO;
import com.gestionprojetagile.ProjetAgile.web.Enities.ProductBacklog;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-24T14:39:33+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class ProductBacklogMapperImpl implements ProductBacklogMapper {

    @Override
    public ProductBacklogDTO productBlToProductBlDto(ProductBacklog productBacklog) {
        if ( productBacklog == null ) {
            return null;
        }

        ProductBacklogDTO productBacklogDTO = new ProductBacklogDTO();

        productBacklogDTO.setId( productBacklog.getId() );
        productBacklogDTO.setName( productBacklog.getName() );

        return productBacklogDTO;
    }

    @Override
    public ProductBacklog productBlDtoToProductBl(ProductBacklogDTO ProductBacklogDTO) {
        if ( ProductBacklogDTO == null ) {
            return null;
        }

        ProductBacklog productBacklog = new ProductBacklog();

        productBacklog.setId( ProductBacklogDTO.getId() );
        productBacklog.setName( ProductBacklogDTO.getName() );

        return productBacklog;
    }
}
