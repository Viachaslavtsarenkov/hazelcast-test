package com.example.hazelcastproject.web;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentMap;

@RestController
public class CommandController {

    @Qualifier("hazelcastInstance")
    @Autowired
    private HazelcastInstance hazelcastInstance;

    private ConcurrentMap<String,String> retrieveMap() {
        return hazelcastInstance.getMap("map");
    }

    @PostMapping("/put")
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value) {
        retrieveMap().put(key, value);
        return value;
    }

    @GetMapping("/get")
    public String get(@RequestParam(value = "key") String key) {
        String value = retrieveMap().get(key);
        return value;
    }
}
