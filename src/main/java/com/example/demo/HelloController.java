package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller; // RestController 아님!
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller // 1. 화면(View)을 돌려주는 컨트롤러로 변경
@RequiredArgsConstructor
public class HelloController {

    private final VisitorRepository visitorRepository;

    @GetMapping("/join")
    public String join(@RequestParam("name") String name) {
        visitorRepository.save(new Visitor(name));
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model) {
        // 2. DB에서 명단 가져오기
        List<Visitor> visitors = visitorRepository.findAll();

        // 3. "visitors"라는 이름으로 명단을 HTML에 전달
        model.addAttribute("visitors", visitors);

        return "index"; // 4. resources/templates/index.html을 찾아가라!
    }
}