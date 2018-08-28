package com.sveticov.apppostgres.controllers;

import com.sveticov.apppostgres.models.Tools;
import com.sveticov.apppostgres.models.Workers;
import com.sveticov.apppostgres.service.ToolsService;
import com.sveticov.apppostgres.service.WorkersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class ToolsController {
    @Autowired
    ToolsService toolsService;
    @Autowired
    WorkersService workersService;

    @GetMapping("/tools/save")
    public String saveToolsGet(Model model) {
        model.addAttribute("tools_model_for_save_form", new Tools());
        return "tools_form_save";
    }

    @PostMapping("/tools/save")
    public String saveToolsPost(@ModelAttribute("tools_model_for_save_form") Tools tools, Model model) {
        toolsService.save(tools);
        model.addAttribute("name_tools_new", tools.getName_tools());
        model.addAttribute("date_tools_new", tools.getDeta_tools());
        model.addAttribute("id_tools_new", tools.getId_tools());

        return "tools_new_model";
    }


//    @GetMapping("/")
//    public String tools(@RequestParam("id") int id) {
//
//        return toolsService.findById(id).toString();
//    }


    @GetMapping("/tools/models/all")
    String allModelsTools(Model model) {
        model.addAttribute("tools_models", toolsService.findAll());

        return "all_tools_model";
    }

    // find model from id
    @GetMapping("/tools/find/id")
    String toolsFindIDget(Model model) {
        model.addAttribute("tools_model_id", new Tools());
        return "all_tools_model";
    }

    @PostMapping("/tools/find/id")
    String toolsFindID(@RequestParam("tools_id_model") int tools_id_model, Model model) {
        log.info(String.valueOf(tools_id_model));
        Tools tools;
        tools = toolsService.findById(tools_id_model).get();

        model.addAttribute("name_tools_new", tools.getName_tools());
        model.addAttribute("date_tools_new", tools.getDeta_tools());
        model.addAttribute("id_tools_new", tools.getId_tools());

        return "tools_new_model";
    }


    //delete model tool
    @GetMapping("/tools/model/delete")
    String toolsDeleteFromID(Model model) {
        model.addAttribute("tools_model_id", new Tools());
        return "all_tools_model";
    }


    @PostMapping("/tools/model/delete")
    String toolsDeleteFromID(@RequestParam("tools_id_model") int tools_id_model) {
        log.info(String.valueOf("model from id_tools is delete: " + tools_id_model));
        Tools tools = toolsService.findById(tools_id_model).get();

        for (Workers workers : workersService.findAll()) {
          boolean turn=  workers.getTools().remove(tools);
          if(turn==true){
              tools.getWorkers().remove(workers);
              workersService.save(workers);
          }
        }

        toolsService.delete(tools);
        return "redirect:/tools/models/all";
    }

    //edit model tool
    @GetMapping("/tools/model/edit")
    String toolsEditFromID(Model model) {
        model.addAttribute("tools_model_id", new Tools());
        return "tools_new_model";
    }

    @PostMapping("/tools/model/edit")
    String toolsEditFromID(@RequestParam("tools_id_model") int tools_id_model, Model model) {
        log.info(String.valueOf("model from id_tools is edit: " + tools_id_model));
        Tools tools;
        tools = toolsService.findById(tools_id_model).get();
        model.addAttribute("tools_model_for_update_form", new Tools());
        model.addAttribute("edit_model_id_tool", tools.getId_tools());
        return "edit_tools_model";
    }

    //update

    @PostMapping("/tools/update/model")
    String toolsUpdateModel(@RequestParam("edit_model_id_tool") int edit_model_id_tool, @ModelAttribute("tools_model_for_update_form") Tools tools) {
        tools.setId_tools(edit_model_id_tool);
        toolsService.updateModelTools(edit_model_id_tool, tools.getName_tools(), tools.getDeta_tools());
        return "redirect:/tools/models/all";
    }
}
