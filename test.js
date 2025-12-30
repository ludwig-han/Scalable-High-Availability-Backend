import http from 'k6/http';
import { sleep, check } from 'k6';

export const options = {
    // 시나리오: 10초 동안 사용자를 0명에서 100명으로 늘리고, 20초 유지한 뒤 종료
    stages: [
        { duration: '10s', target: 100 },
        { duration: '20s', target: 100 },
        { duration: '5s', target: 0 },
    ],
};

export default function () {
    const name = `user_${Math.floor(Math.random() * 100000)}`;

    // 1. 등록 및 리다이렉트 (쓰기 + 읽기 복합 부하)
    const res = http.get(`http://localhost:8080/join?name=${name}`);

    // 2. 정상 응답(200 OK)이 오는지 검증
    check(res, {
        'is status 200': (r) => r.status === 200,
    });

    sleep(1); // 실제 사용자의 행동 간격을 위해 1초 대기
}