package com.example.accesskeybackend.template.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.*;

@RestController
@RequestMapping("/api/web")
public class Ipv6SupportController {
    @GetMapping("/checkIpv6Support")
    public ResponseEntity<String> checkIpv6Support(@RequestParam("siteUrl") String siteUrl) throws UnknownHostException {
        InetAddress inetAddress = null;
        Boolean isIPv6 = null;
        if (siteUrl.matches("^^(http|https|ftp):\\/\\/.*$")) {
            try {
                URI uri = new URI(siteUrl);
                inetAddress = InetAddress.getByName(uri.getHost());
                isIPv6 = (inetAddress instanceof Inet6Address);
                return ResponseEntity.ok("success - " + isIPv6);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } else {
            inetAddress = InetAddress.getByName(siteUrl);
            isIPv6 = (inetAddress instanceof Inet6Address);
            return ResponseEntity.ok("success - " + isIPv6);
        }
    }
}
