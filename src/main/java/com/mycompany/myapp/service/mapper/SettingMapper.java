package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.SettingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Setting} and its DTO {@link SettingDTO}.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class, ZoneMapper.class})
public interface SettingMapper extends EntityMapper<SettingDTO, Setting> {

    @Mapping(source = "countryId.id", target = "countryIdId")
    @Mapping(source = "zoneId.id", target = "zoneIdId")
    SettingDTO toDto(Setting setting);

    @Mapping(source = "countryIdId", target = "countryId")
    @Mapping(source = "zoneIdId", target = "zoneId")
    Setting toEntity(SettingDTO settingDTO);

    default Setting fromId(Long id) {
        if (id == null) {
            return null;
        }
        Setting setting = new Setting();
        setting.setId(id);
        return setting;
    }
}
