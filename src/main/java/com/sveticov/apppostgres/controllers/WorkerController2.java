package com.sveticov.apppostgres.controllers;

import com.sveticov.apppostgres.models.SecurityUser;
import com.sveticov.apppostgres.models.Tools;
import com.sveticov.apppostgres.models.Workers;
import com.sveticov.apppostgres.service.ToolsService;
import com.sveticov.apppostgres.service.WorkersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Slf4j
@Controller
public class WorkerController2 {
    @Autowired
    WorkersService workersService;
    @Autowired
    ToolsService toolsService;

    @GetMapping("/")
    String myWay() {
        return "start_page";
    }

    @GetMapping("/welcome")
    String welcomeWorkTool(Model model) {
        model.addAttribute("security_user_model", new SecurityUser());
        return "welcome_page";
    }

    @PostMapping("/welcome")
    String welcomeWorkToolPost(@ModelAttribute("security_user_model") SecurityUser security_user_model) {
        log.info(security_user_model.toString());
        return "welcome_page";
    }

    @GetMapping("/workers/find/id")
    public String getWorker(Model model) {
        model.addAttribute("workers", new Workers());
        return "SurchWorker";
    }


    @PostMapping("/workers/find/id")
    public String postWorks(@ModelAttribute Workers workers, Model model) {

        int id = workers.getId_workers();
        log.info(" +/workers/find/id+ worker id: " + id + "  " + workersService.findNameById(id));
//
        Optional<Workers> optionalWorkers;
        try {
            optionalWorkers = workersService.findById(id);
            workers = optionalWorkers.get();
            log.info("+/workers/find/id+ find worker from id: " + workers.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "SurchWorker";
        }

        model.addAttribute("worker_name", workers.getName_worker());
        model.addAttribute("worker_last_name", workers.getLast_name_worker());

//
        myChart(model);


        return "AllWorkers";
    }


    //
    @GetMapping("/workers/vfind/id")
    public String getWorker2(Model model) {
        model.addAttribute("workers2", new Workers());
        return "AllWorkersModel";
    }

    @PostMapping("/workers/vfind/id")
    public String postWorks2(@RequestParam("id_w") int id, Model model) {

        Workers workers = new Workers();
        // log.info(" +/workers/find/id+ worker id: "+id+"  "+workersService.findNameById(id));
//
        Optional<Workers> optionalWorkers;
        try {
            optionalWorkers = workersService.findById(id);
            workers = optionalWorkers.get();
            // log.info("+/workers/vfind/id+ find worker from id: "+workers.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "SurchWorker";
        }

        model.addAttribute("worker_name", workers.getName_worker());
        model.addAttribute("worker_last_name", workers.getLast_name_worker());
        model.addAttribute("worker_email", workers.getEmail_worker());
        model.addAttribute("worker_tools", workers.getTools());

//
        myChart(model);


        return "AllWorkers";
    }

    @GetMapping("/workers/new/save")
    public String getSaveW(Model model) {
        model.addAttribute("workers", new Workers());
        model.addAttribute("tools", toolsService.findAll());
        return "SurchWorker";
    }

    @PostMapping("/workers/new/save")
    public String postSaveW(@RequestParam(value = "tool_id") int[] tool_id, @Valid @ModelAttribute("workers") Workers workers,
                            BindingResult bindingResult, Model model) {
        Set<Tools> toolsSet = new HashSet<>();

        for (int id : tool_id) {
            log.info("tool_id " + id);
            Optional<Tools> tools = toolsService.findById(id);
            toolsSet.add(tools.get());

        }

        if (bindingResult.hasErrors()) {
            log.info("+/workers/new/save+ binding: " + bindingResult.toString());
            return "SurchWorker";
        }
        //  log.info("+/workers/new/save+ add new worker: " + workers.toString());
        workers.setTools(toolsSet);
        workersService.save(workers);
        model.addAttribute("worker_name", workers.getName_worker());
        model.addAttribute("worker_last_name", workers.getLast_name_worker());
        model.addAttribute("worker_email", workers.getEmail_worker());
        model.addAttribute("worker_tools", workers.getTools());

        myChart(model);

        return "AllWorkers";
    }


    @GetMapping("/workers/all/model")
    public String allModelWorkers(Model model) {
        model.addAttribute("test", " test");
        model.addAttribute("all_models_workers", workersService.findAll());
        return "AllWorkersModel";
    }


    public void myChart(Model model) {
        String[] arr_str = {"a", "b", "c", "g", "f", "78", "15"};
        int[] arr_int2 = {7, 4, 9, 0, 2, 1, 5};
        int[] arr_int = {1, 3, 5, 8, 9, 5, 0};
        Random rnd = new Random();
        for (int i = 0; i < arr_str.length; i++) {
            arr_int2[i] = rnd.nextInt(20);

        }
        for (int i = 0; i < arr_str.length; i++) {
            arr_int[i] = rnd.nextInt(20);
        }


        model.addAttribute("str", arr_str);
        model.addAttribute("num", arr_int);
        model.addAttribute("num2", arr_int2);
    }
}
