package com.example.Population.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.sql.ResultSet;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class County {
    private Integer id;
    private String name;
    private Region region;

    @SneakyThrows
    public static County create(ResultSet rs) {

        if(rs.getInt("county_id") == 0) {
            return null;
        }

        var county = new County(rs.getInt("county_id"),
                rs.getString("county_name"),
                Region.create(rs)
        );
        return county;
    }


}