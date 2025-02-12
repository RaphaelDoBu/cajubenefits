# Desafio Técnico

## Tecnologias utilizadas
Kotlin 1.4.10
SpringFramework 2.4.0
JUnit
Mockito

## Instruções para executar o projeto
Abaixo segue um curl de exemplo: 

```json
    curl --location 'localhost:8080/transactions/authorize' \
    --header 'Content-Type: application/json' \
    --data '{
    "accountId": "123",
    "totalAmount": 1000.00,
    "mcc": "5811",
    "merchant": "UBER EATS                   SAO PAULO BR"
    }'
```
