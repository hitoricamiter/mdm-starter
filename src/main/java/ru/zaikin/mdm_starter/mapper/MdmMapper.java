package ru.zaikin.mdm_starter.mapper;

import ru.zaikin.mdm_starter.dto.MdmResponse;
import ru.zaikin.mdm_starter.model.ClientInfo;

public class MdmMapper {

    public ClientInfo toClientInfo(MdmResponse response) {
        if (response == null) return null;

        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setName(response.getName());
        clientInfo.setInn(response.getInn());
        clientInfo.setId(response.getInn());

        return clientInfo;

    }
}
