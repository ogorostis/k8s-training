package com.vnet.samples.k8s;

import com.vnet.common.VException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/things")
public class Controller {
    @Value("${app.message}")
    private String message;

    @GetMapping(value = "/home")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> home() {
        return new HashMap<>() {{
            put("message", message);
        }};
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> echo(@PathVariable final int id) {
        try {
            return new HashMap<>() {{
                put("id", id);
                put("ts", System.currentTimeMillis());
                put("ip", Inet4Address.getLocalHost());
                put("java", new HashMap<>() {{
                    put("home", System.getProperty("java.home"));
                    put("version", System.getProperty("java.version"));
                    put("vendor", System.getProperty("java.vendor"));
                }});
                put("user", new HashMap<>() {{
                    put("home", System.getProperty("user.home"));
                    put("dir", System.getProperty("user.dir"));
                    put("name", System.getProperty("user.name"));
                }});
                put("os", new HashMap<>() {{
                    put("arch", System.getProperty("os.arch"));
                    put("version", System.getProperty("os.version"));
                    put("name", System.getProperty("os.name"));
                }});
            }};
        } catch (IOException e) {
            throw new VException(e.getMessage());
        }
    }

    @ControllerAdvice
    static class ThingsExceptionHandler extends ResponseEntityExceptionHandler {
        @ExceptionHandler(value = RuntimeException.class)
        ResponseEntity<Object> rte(final RuntimeException rte, final WebRequest request) {
            return handleExceptionInternal(rte,
                    new HashMap<String, String>() {{put("error", rte.getMessage());}},
                    new HttpHeaders(), HttpStatus.NOT_FOUND, request);
        }
    }
}
