# Music Manager API

## 🎵 Sobre o Projeto
A **Music Manager API** é um sistema profissional para gerenciamento de Artistas e Álbuns.
O foco deste projeto é **simplicidade de execução** aliada a uma **arquitetura robusta** (Java, Spring Boot, Docker).

---

## 🚀 Como Executar (Guia Passo-a-Passo)

Preparamos instaladores automáticos para facilitar sua vida. Escolha seu sistema operacional abaixo:

### 🪟 Windows

#### Opção 1: Instalação Automática (Recomendada)
Ideal para quem quer praticidade. O script verificará se você tem o Docker instalado e configurará tudo sozinho.

1. Abra a pasta do projeto.
2. Dê dois cliques no arquivo **`Instalar.bat`**.
3. Se solicitado, aceite a permissão de Administrador.
4. Aguarde o processo finalizar. Uma janela preta mostrará o progresso.
5. Ao final, o navegador abrirá automaticamente com o sistema pronto.

> **Nota:** Se você não tiver o Docker instalado, o script perguntará se deseja instalar. Digite `S` e enter.

#### Opção 2: Instalação Manual
Caso prefira fazer do seu jeito:

1. Baixe e instale o [Docker Desktop para Windows](https://www.docker.com/products/docker-desktop).
2. Abra o Docker Desktop e espere ele iniciar.
3. Abra o terminal (PowerShell ou CMD) na pasta do projeto.
4. Execute:
   ```powershell
   docker compose up -d --build
   ```
5. Acesse: `http://localhost:8080/swagger-ui.html`

---

### 🐧 Linux

#### Opção 1: Instalação Automática (Recomendada)
1. Abra o terminal na pasta do projeto.
2. Dê permissão de execução ao script:
   ```bash
   chmod +x install.sh
   ```
3. Execute o instalador:
   ```bash
   ./install.sh
   ```
4. O script verificará o Docker e subirá o sistema.

#### Opção 2: Instalação Manual
1. Certifique-se de ter o Docker e o Docker Compose instalados na sua distribuição.
2. Execute no terminal:
   ```bash
   docker compose up -d --build
   ```

---

## 🛠️ Tecnologias
- **Java 17 & Spring Boot 3**: O coração da aplicação.
- **PostgreSQL**: Banco de dados robusto.
- **MinIO**: Armazenamento de imagens (compatível com S3).
- **Docker**: Para garantir que funcione em qualquer máquina.
- **Flyway**: Migrações de banco de dados seguras.

## 📚 Documentação (API)
Após iniciar, a documentação completa dos endpoints estará disponível em:
👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

## 👤 Login Padrão
Para testar os endpoints protegidos:
- **Usuário:** `admin`
- **Senha:** `admin`
