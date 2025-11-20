# 🎟️ Coupon API

API REST desenvolvida como parte de um desafio técnico.  
O sistema gerencia o ciclo de vida de cupons de desconto — **criação**, **consulta** e **soft delete**.

---

## 🚀 Tecnologias Utilizadas

- **Java 17 (LTS)**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database** (banco em memória)
- **Bean Validation**
- **JUnit 5 & Mockito** (testes unitários)

---

## 🗄️ H2 Console

A aplicação inicia na porta **8080**.

**URL:** http://localhost:8080/h2-console

Credenciais:

- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User:** `sa`
- **Password:** *(vazio)*

---

## 🧪 Executando os Testes

```bash
./mvnw test
````
## 📚 Endpoints da API

### 1. Criar Cupom  
**POST /coupon**

**Exemplo de requisição:**
```json
{
  "code": "abc-123",
  "description": "Descricao",
  "discountValue": 0.5,
  "expirationDate": "2025-11-20T20:20:45.180Z",
  "published": true
}
````

### 2. Buscar Cupom por ID  
**GET /coupon/{id}**  
Retorna os dados completos do cupom.

---

### 3. Soft Delete do Cupom  
**DELETE /coupon/{id}**  
- Retorna **204 No Content**  
- Marca o status como **DELETED** (não remove do banco)

---

## 🧱 Regras de Negócio

- Código do cupom deve ter **exatamente 6 caracteres após sanitização**  
- Caracteres especiais são **removidos automaticamente**  
- Código sempre salvo em **UPPERCASE**  
- Soft delete altera o status para **DELETED**

---

## 🧪 Testes Unitários

Testes escritos utilizando:

- **JUnit 5**
- **Mockito**

Cobrem:

- Sanitização do código  
- Criação  
- Busca  
- Soft delete
