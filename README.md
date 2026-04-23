# 🛒 E-Commerce Microservices (Event-Driven with Kafka)

## 📌 Overview

Project ini merupakan implementasi **arsitektur microservices berbasis event-driven** menggunakan Apache Kafka dengan pola **Choreography**.

Setiap service berjalan secara independen dan berkomunikasi melalui event (Kafka), tanpa central orchestrator.

---

## 🏗️ Architecture

```text
Order Service → Kafka → Payment Service → Kafka → Notification Service
```

### Event Flow:

* `order-created` → dipublish oleh Order Service
* `payment-processed` → dipublish oleh Payment Service

---

## ⚙️ Tech Stack

* Java 21
* Spring Boot
* Apache Kafka (KRaft mode)
* MySQL (XAMPP)
* Maven
* VS Code
* Postman (API Testing)

---

## 📦 Microservices

### 1️⃣ Auth Service

* Register & Login user
* Generate JWT Token
* Role-based authentication (ADMIN / USER)

---

### 2️⃣ Product Service

* CRUD produk
* Endpoint dilindungi JWT (khusus ADMIN untuk create/update/delete)
* Integrasi dengan Auth Service

---

### 3️⃣ Order Service

* Create order
* Mengirim event ke Kafka (`order-created`)
* Menggunakan data produk

---

### 4️⃣ Payment Service

* Consume event `order-created`
* Memproses pembayaran
* Menyimpan data ke database
* Mengirim event `payment-processed`

---

### 5️⃣ Notification Service

* Consume event `payment-processed`
* Menampilkan notifikasi pembayaran sukses (log)

---

## 🔁 Event-Driven Flow Detail

### Step 1: Order dibuat

Order Service:

```json
{
  "id": 1,
  "productId": 2,
  "quantity": 2,
  "totalPrice": 30000000,
  "status": "CREATED"
}
```

→ publish ke topic: `order-created`

---

### Step 2: Payment diproses

Payment Service:

* Consume `order-created`
* Generate payment

```json
{
  "orderId": 1,
  "amount": 30000000,
  "status": "PAID"
}
```

→ publish ke topic: `payment-processed`

---

### Step 3: Notification

Notification Service:

* Consume `payment-processed`
* Menampilkan:

```
🔔 NOTIFICATION: Payment sukses untuk Order ID 1 sebesar 30000000
```

---

## 🚀 Cara Menjalankan Project

### 1. Jalankan Kafka (KRaft)

Pastikan Kafka sudah running di:

```
localhost:9092
```

---

### 2. Jalankan MySQL (XAMPP)

Buat database:

* auth_db
* product_db
* order_db
* payment_db

---

### 3. Jalankan Semua Service

Masuk ke masing-masing folder:

```bash
mvn spring-boot:run
```

Urutan disarankan:

1. auth-service
2. product-service
3. order-service
4. payment-service
5. notification-service

---

## 🧪 Testing API

Gunakan Postman:

### Auth

* POST `/auth/login` → dapat token

### Product

* GET `/products`
* POST `/products` (ADMIN only)

### Order

* POST `/orders`

---

## 📨 Kafka Topics

* `order-created`
* `payment-processed`

---

## 🧠 Key Concepts Implemented

* Microservices Architecture
* Event-Driven System
* Kafka Producer & Consumer
* Choreography Pattern
* Loose Coupling antar service
* Asynchronous Communication

---

## ⚠️ Challenges & Solutions

### ❌ Masalah

* Kafka deserialization error (ClassNotFound)
* Offset conflict
* Serializer mismatch

### ✅ Solusi

* Gunakan String untuk consumer
* Gunakan JsonSerializer untuk producer
* Disable type header Kafka
* Gunakan group-id baru untuk reset offset

---

## 🔮 Future Improvements

* Email Notification Service
* WebSocket real-time notification
* Retry mechanism & Dead Letter Queue (DLQ)
* API Gateway (Spring Cloud Gateway)
* Service Discovery (Eureka)
* Docker & Docker Compose
* Centralized Logging (ELK Stack)
* Monitoring (Prometheus + Grafana)

---

## 👩‍💻 Author

Alia — E-Commerce Microservices Project

---

## 📌 Notes

Project ini dibuat sebagai implementasi pembelajaran:

* Enterprise Application Integration
* Microservices Architecture
* Event-Driven System

---
## 🆕 Shipping Service

### 📦 6️⃣ Shipping Service

* Consume event `payment-processed`
* Membuat data pengiriman (shipping)
* Menyimpan ke database
* Publish event `shipping-created`

---

## 🔁 Final Event Flow (Complete)

```text
Order Service 
   ↓ (publish)
order-created
   ↓
Payment Service 
   ↓ (publish)
payment-processed
   ↓
Shipping Service 
   ↓ (publish)
shipping-created
   ↓
Notification Service
```

---

## 🧪 End-to-End Testing Result

Berikut adalah hasil pengujian real:

### ✅ Payment → Shipping (Log)

```text
PAYMENT RECEIVED: {"id":8,"orderId":10,"amount":1.5E7,"status":"PAID"}
SHIPPING CREATED: Shipping(id=2, orderId=10, address=Default Address, status=SHIPPED)
```

---

### ✅ Kafka Topic Output (`shipping-created`)

```json
{"id":2,"orderId":10,"address":"Default Address","status":"SHIPPED"}
```

---

## 📨 Kafka Topics (Updated)

* `order-created`
* `payment-processed`
* `shipping-created`

---

## 🧠 Architecture Pattern

Project ini menggunakan:

### 🔹 Choreography Pattern

* Tidak ada orchestrator
* Setiap service bereaksi terhadap event
* Loose coupling antar service

---

## 📈 System Characteristics

* Asynchronous communication
* Scalable (mudah tambah service baru)
* Decoupled architecture
* Event-driven processing

---

## 🔮 Future Improvements (Extended)

* Email / WhatsApp Notification
* Real-time notification (WebSocket)
* Dead Letter Queue (DLQ)
* Retry mechanism
* API Gateway
* Service Discovery (Eureka)
* Dockerization
* Centralized Logging
* Monitoring (Prometheus + Grafana)

---

## ✅ Final Status

✔ Auth Service
✔ Product Service
✔ Order Service
✔ Payment Service
✔ Shipping Service
✔ Notification Service

🔥 **FULL EVENT-DRIVEN MICROSERVICES SYSTEM WORKING**
