# Arabsoft Summer Internship 2024

This repository contains a full-stack solution for automated document OCR, classification, and structured data extraction. The system is composed of a modern web frontend, a robust Java backend, and a high-performance Go/Python OCR microservice. The architecture is designed for modularity, scalability, and ease of integration in enterprise environments.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Directory Structure](#directory-structure)
- [Setup & Installation](#setup--installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## Project Overview

This project provides an end-to-end pipeline for uploading, processing, and extracting structured data from financial documents using OCR and AI. It is designed for scalability, modularity, and ease of use, leveraging modern technologies across the stack.

---

## Architecture

- **Frontend:** Built with [Astro](https://astro.build/), providing a fast, modern, and responsive user interface.
- **Backend:** Java (Spring Boot) REST API for business logic, document management, and integration.
- **OCR Microservice:** Go HTTP server orchestrating Python-based OCR and document structuring using state-of-the-art AI models.

---

## Directory Structure

```
/
├── Arabsoft-FrontEnd/      # Astro-based web frontend
├── Arabsoft-BackEnd/       # Java Spring Boot backend
│   └── OCR/                # OCR backend module
├── Service-OCR/            # OCR microservice
│   └── ServiceOCR/         # Go and Python OCR logic
├── .gitignore
└── README.md
```

---

## Setup & Installation

### 1. Clone the Repository

```sh
git clone https://github.com/safwen5ds/arabsoft-summer-internship-2024.git
cd arabsoft-summer-internship-2024
```

### 2. Frontend

```sh
cd Arabsoft-FrontEnd
npm install
npm run dev
```
Visit `http://localhost:4321` to view the frontend.

### 3. Backend

```sh
cd ../Arabsoft-BackEnd/OCR
./mvnw spring-boot:run
```
The backend API will be available at `http://localhost:8080`.

### 4. OCR Microservice

- **Requirements:** Go, Python 3, doctr, pdf2image, Google Generative AI SDK, and Tesseract.
- Set your `API_KEY` environment variable for Google Generative AI.
- Run the Go server:

```sh
cd ../../Service-OCR/ServiceOCR
go run OCR.go
```
The OCR service will listen on `http://localhost:8081/OCR`.

---

## Usage

1. Upload a PDF document via the frontend.
2. The backend routes the file to the OCR microservice.
3. The OCR service extracts and structures the text using AI.
4. The backend stores and serves the structured data.
5. The frontend displays the results.

---

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request.

---

## License

This project is licensed under the MIT License.