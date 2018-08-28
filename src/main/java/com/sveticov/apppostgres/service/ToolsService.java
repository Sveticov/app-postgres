package com.sveticov.apppostgres.service;

import com.sveticov.apppostgres.models.Tools;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
public interface ToolsService extends CrudRepository<Tools, Integer> {
    //    @Query(value = "select name_tools,deta_tools from tools where id_tools=1",nativeQuery = true)
//    Tools findOn(int i);


    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tools SET name_tools=:name_tools,deta_tools=:deta_tools WHERE id_tools=:id_tools", nativeQuery = true)
    void updateModelTools(@Param("id_tools") int id_tools, @Param("name_tools") String name_tools, @Param("deta_tools") String deta_tools);

    @Transactional
    @Query(value = "DELETE FROM tools WHERE id_tools=:id_tools_remove", nativeQuery = true)
    void removeTools(@Param("id_tools_remove") int id_tools_remove);

    @Transactional
    @Query(value = "delete from workers_tools where id_tw=:id_tools_remove", nativeQuery = true)
    void removeToolsWorkers(@Param("id_tools_remove") int id_tools_remove);
}
