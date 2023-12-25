package com.controller;

import com.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/shawky")
public class References {
    @GetMapping("/headers")
    public String getHeaders(@RequestHeader Map<String, String> headers) {
        String result = "";
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            result += "Key: " + entry.getKey() + " - value: " + entry.getValue() + "<br/>";
        }
        return result;
    }

    @GetMapping("/param")
    public String getParam(@RequestParam("name") String name) {
        return "Getting student param name = " + name;
    }

    @GetMapping("/students/{id}")
    public String getPathVariable(@PathVariable("id") int id) {
        return "Getting student of id = " + id;
    }

    @GetMapping("/add")
    public Response parseBody() {
        return null;
    }

}
