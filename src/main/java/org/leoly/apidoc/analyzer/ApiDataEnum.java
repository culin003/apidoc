package org.leoly.apidoc.analyzer;

import org.leoly.apidoc.utils.ApiConstants;
import org.leoly.apidoc.utils.ApiUtils;

import java.util.Date;

public enum ApiDataEnum {

    STRING(String.class.getSimpleName()),
    INTEGER(Integer.class.getSimpleName()),
    DATETIME(Date.class.getSimpleName(), "yyyy-MM-dd HH:mm:ss"),
    DATEMINUTE(Date.class.getSimpleName(), "yyyy-MM-dd HH:mm"),
    DATE(Date.class.getSimpleName(), "yyyy-MM-dd"),
    DATEMONTH(Date.class.getSimpleName(), "yyyy-MM"),
    LONG(Long.class.getSimpleName());

    private String type;
    private String format;

    private ApiDataEnum(String type) {
        this.type = type;
    }

    private ApiDataEnum(String type, String format) {
        this.type = type;
        this.format = format;
    }

    public String desc(String desc) {
        return this.type + (ApiUtils.isEmpty(this.format) ? ApiConstants.EMPTY_STRING : ("，" + this.format))
                + (ApiUtils.isEmpty(desc) ? ApiConstants.EMPTY_STRING : ("，" + desc));
    }

    public String desc(int length, String desc) {
        return this.type + (length > 0 ? "(" + length + ")" : ApiConstants.EMPTY_STRING) +
                (ApiUtils.isEmpty(this.format) ? ApiConstants.EMPTY_STRING : ("，" + this.format))
                + (ApiUtils.isEmpty(desc) ? ApiConstants.EMPTY_STRING : ("，" + desc));
    }
}
