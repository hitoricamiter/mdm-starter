package ru.zaikin.mdm_starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mdm")
public class MdmProperties {


    /*mdm:
  base-url: https://mdm.company.internal
  lookup-path: /api/v1/lookup
  token: tosibosi812731ja@!#!UweiqwheiqwOrej
*/

    /**
     * Базовый URL сервиса MDM.
     * Например: https://mdm.company.internal
     * К нему будет добавляться путь lookupPath для вызова API.
     */

    private String baseUrl;

    /**
     * Путь для запроса поиска клиента в MDM.
     * Например: /lookup или /api/v1/client/lookup
     * Этот путь будет добавляться к baseUrl при формировании полного URL.
     */

    private String lookupPath;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getLookupPath() {
        return lookupPath;
    }

    public void setLookupPath(String lookupPath) {
        this.lookupPath = lookupPath;
    }
}
