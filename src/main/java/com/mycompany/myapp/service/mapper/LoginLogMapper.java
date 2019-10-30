package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LoginLogDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LoginLog} and its DTO {@link LoginLogDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LoginLogMapper extends EntityMapper<LoginLogDTO, LoginLog> {



    default LoginLog fromId(Long id) {
        if (id == null) {
            return null;
        }
        LoginLog loginLog = new LoginLog();
        loginLog.setId(id);
        return loginLog;
    }
}
