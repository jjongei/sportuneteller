package com.sportuenteller.olympic.common.excel.player;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerInfo {
    private long id;
    private String code;
    private String name;
    private boolean sexuality;
    private String countryCode;
    private String countryReference;
    private boolean modify;

    public PlayerInfo(long id, String code, String name, boolean sexuality, String countryCode, String countryReference, boolean modify) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.sexuality = sexuality;
        this.countryCode = countryCode;
        this.countryReference = countryReference;
        this.modify = modify;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerInfo that = (PlayerInfo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
