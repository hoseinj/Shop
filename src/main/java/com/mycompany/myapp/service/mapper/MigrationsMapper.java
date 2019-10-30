package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MigrationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Migrations} and its DTO {@link MigrationsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MigrationsMapper extends EntityMapper<MigrationsDTO, Migrations> {



    default Migrations fromId(Long id) {
        if (id == null) {
            return null;
        }
        Migrations migrations = new Migrations();
        migrations.setId(id);
        return migrations;
    }
}
