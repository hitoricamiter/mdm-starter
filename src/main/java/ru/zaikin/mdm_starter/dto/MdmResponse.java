package ru.zaikin.mdm_starter.dto;

// DTO для ответа от MDM

public class MdmResponse {
    private String id;
    private String name;
    private String inn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
}
