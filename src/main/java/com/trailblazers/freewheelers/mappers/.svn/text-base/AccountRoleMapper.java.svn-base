package com.trailblazers.freewheelers.mappers;

import com.trailblazers.freewheelers.model.AccountRole;
import org.apache.ibatis.annotations.*;

public interface AccountRoleMapper {

    @Insert(
        "INSERT INTO account_role (account_name, role) VALUES (#{account_name}, #{role})"
    )
    @Options(keyProperty = "role_id", useGeneratedKeys = true)
    void insert(AccountRole accountRole);

    @Select( "SELECT role from account_role where account_name = #{accountName}")
    @Results(value ={@Result(property="role")})
    AccountRole getByAccountName(String accountName);
}
