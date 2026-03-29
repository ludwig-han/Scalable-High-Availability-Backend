package com.example.demo.controller;

import com.example.demo.entity.TestData;
import com.example.demo.repository.TestDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class LoadTestController {

    private final TestDataRepository testDataRepository;

    /**
     * 1. 가벼운 부하 (Network/Framework Check)
     * 로직 없이 즉시 응답하여 기본적인 초당 처리량(TPS)을 측정합니다.
     */
    @GetMapping("/light")
    public String light() {
        return "ok\n`";
    }

    /**
     * 2. CPU 부하 (CPU-Intensive)
     * 복잡한 수학 연산을 반복하여 서버의 CPU 점유율을 강제로 높입니다.
     */
    @GetMapping("/cpu")
    public String cpu() {
        double result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += Math.atan(Math.sqrt(i));
        }
        return String.valueOf(result) + "\n";
    }

    /**
     * 3. I/O 대기 부하 (Thread Pool Exhaustion)
     * 의도적으로 응답을 지연시켜 톰캣의 스레드가 꽉 차는 상황을 시뮬레이션합니다.
     */
    @GetMapping("/io-wait")
    public String ioWait() throws InterruptedException {
        // 0.5초 동안 일꾼(Thread)을 붙잡아 둡니다.
        Thread.sleep(500);
        return "delayed ok\n";
    }

    /**
     * 4. DB 쓰기 부하 (Database Write / Connection Pool)
     * 새로운 데이터를 DB에 저장하며 커넥션 풀과 DB 쓰기 성능을 테스트합니다.
     */
    @PostMapping("/db-write")
    public TestData dbWrite() {
        log.info("Writing data to MySQL...");
        return testDataRepository.save(new TestData("Test Content at " + System.currentTimeMillis()));
    }

    /**
     * 5. DB 읽기 부하 (Database Read / HikariCP)
     * 전체 데이터를 조회합니다. 데이터량이 많아질수록 응답 시간이 길어집니다.
     */
    @GetMapping("/db-read")
    public List<TestData> dbRead() {
        log.info("Reading all data from MySQL...");
        return testDataRepository.findAll();
    }
}