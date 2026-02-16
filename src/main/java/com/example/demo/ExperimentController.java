package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class ExperimentController {
    private final VisitorRepository visitorRepository;

    // [Case 1, 2용] CPU 부하: 소수 계산 (CPU-Bound)
    @GetMapping("/cpu")
    public String cpuLoad(@RequestParam(defaultValue = "100000") int range) {
        int count = 0;
        for (int i = 2; i <= range; i++) {
            boolean isPrime = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) { isPrime = false; break; }
            }
            if (isPrime) count++;
        }
        return "Calculated " + count + " primes";
    }

    // [Case 3용] I/O 부하: 쓰레드 대기 (I/O-Bound)
    // 수평 확장 시 쓰레드 효율성을 보기 좋습니다.
    @GetMapping("/io")
    public String ioLoad() throws InterruptedException {
        Thread.sleep(500); // 0.5초간 쓰레드 점유
        return "I/O Task Completed";
    }

    // [Case 4용] DB 부하: 전체 조회 및 처리
    @GetMapping("/db")
    public List<Visitor> dbLoad() {
        return visitorRepository.findAll();
    }

    @GetMapping("/setup")
    public String setupData() {
        List<Visitor> bulk = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            bulk.add(new Visitor("User-" + i));
        }
        visitorRepository.saveAll(bulk);
        return "5000 rows inserted!";
    }
}