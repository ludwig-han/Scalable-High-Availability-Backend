package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final VisitorRepository visitorRepository;

    @GetMapping("/home")
    public String home(Model model) throws UnknownHostException {
        model.addAttribute("visitors", visitorRepository.findAll());

        // 현재 서버의 호스트네임(또는 IP)을 가져와서 화면에 전달
        // 수평 확장 시 어떤 서버가 응답 중인지 확인하는 핵심 지표가 됩니다.
        String serverName = InetAddress.getLocalHost().getHostName();
        model.addAttribute("serverName", serverName);

        return "index";
    }

    @GetMapping("/join")
    public String join(@RequestParam("name") String name) {
        visitorRepository.save(new Visitor(name));
        return "redirect:/home";
    }
}