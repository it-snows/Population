package com.example.Population.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.sql.ResultSet;

@Data
@AllArgsConstructor
public class Population {

    private Integer id;
    private Integer year;
    private Integer population;
    private City city;

    @SneakyThrows
    public static Population create(ResultSet rs) {
        var pop = new Population(rs.getInt("pop_id"),
                rs.getInt("pop_year"),
                rs.getInt("pop_number"),
                City.create(rs)
        );
        return pop;
    }

    @SneakyThrows
    public static Population create(ResultSet rs, City city) {
        var pop = new Population(rs.getInt("pop_id"),
                rs.getInt("pop_year"),
                rs.getInt("pop_number"),
                city
        );
        return pop;
    }
}