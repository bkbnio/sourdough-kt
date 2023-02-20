import http from 'k6/http';

export const options = {
    stages: [
        { target: 100, duration: '5s' },
        { target: 500, duration: '5s' },
        { target: 1000, duration: '5s' },
        { target: 500, duration: '5s' },
        { target: 100, duration: '5s' },
    ],
    thresholds: {
        http_req_failed: ['rate<0.001'],
        http_req_duration: ['p(95)<100'],
    },
};

export default function () {
    const url = 'http://localhost:8080/author';
    const payload = JSON.stringify({
        name: 'name',
    });

    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    http.post(url, payload, params);
}
