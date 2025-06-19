package ru.zaikin.mdm_starter.dto;

// DTO для запроса к MDM

public class MdmRequest {
    private String clientIdl;


    public MdmRequest(String clientIdl) {
        this.clientIdl = clientIdl;
    }

    public MdmRequest() {
    }

    public String getClientIdl() {
        return clientIdl;
    }

    public void setClientIdl(String clientIdl) {
        this.clientIdl = clientIdl;
    }
}
