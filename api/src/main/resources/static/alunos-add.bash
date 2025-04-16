curl -X POST http://localhost:8080/biblioteca/alunos \
-H "Content-Type: application/json" \
-d '{
  "nome": "Eduardo Martins",
  "matricula": "20060611",
  "email": "dudu.martins@example.com"
}'
