package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.Country;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CountryMapper {
    @Select(
            "SELECT country_id, country_name FROM country"
    )
    @Results(value = {
            @Result(property="country_id"),
            @Result(property="country_name")
    })
    public List<Country> findAll();

    @Select(
            "SELECT country_id, country_name " +
                    "FROM country " +
                    "WHERE country_id = #{country_id}"
    )
    Country getById(long i);
}
