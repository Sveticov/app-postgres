package com.sveticov.apppostgres.service;

import com.sveticov.apppostgres.models.Workers;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WorkersService extends CrudRepository<Workers,Integer> {
    @Query(value = "select name_worker from workers where id_workers=:id_w",nativeQuery = true)
    String findNameById(@Param("id_w")int id_w);


}
