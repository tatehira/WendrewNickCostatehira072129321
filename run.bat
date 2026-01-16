@echo off
setlocal

TITLE Music Manager API - Launcher

echo ===================================================
echo      Iniciando Music Manager API (Docker)
echo ===================================================
echo.

REM 1. Verifica se o Docker está instalado
docker --version >nul 2>&1
if %errorlevel% neq 0 (
    color 0C
    echo [ERRO] O Docker nao foi encontrado no seu sistema.
    echo Por favor, instale o Docker Desktop para Windows e tente novamente.
    echo Download: https://www.docker.com/products/docker-desktop
    echo.
    pause
    exit /b
)

REM 2. Verifica se o Docker Daemon está rodando
docker info >nul 2>&1
if %errorlevel% neq 0 (
    color 0E
    echo [ALERTA] O Docker parece estar fechado.
    echo Tentando iniciar o Docker Desktop...
    
    REM Tenta iniciar o Docker Desktop (caminho padrao comum)
    if exist "C:\Program Files\Docker\Docker\Docker Desktop.exe" (
        start "" "C:\Program Files\Docker\Docker\Docker Desktop.exe"
        echo Aguardando o Docker iniciar (pode levar alguns instantes)...
        timeout /t 20 >nul
    ) else (
        echo Nao foi possivel iniciar o Docker automaticamente.
        echo Por favor, abra o Docker Desktop manualmente e tente novamente.
        pause
        exit /b
    )
)

echo [OK] Docker esta rodando.
echo.

echo ===================================================
echo      Subindo conteineres (App + Banco + MinIO)
echo      Isso pode demorar um pouco na primeira vez.
echo ===================================================
echo.

REM 3. Executa o Docker Compose
docker compose up -d --build

if %errorlevel% neq 0 (
    color 0C
    echo.
    echo [ERRO] Falha ao subir os conteineres.
    echo Verifique o log acima para mais detalhes.
    pause
    exit /b
)

echo.
echo ===================================================
echo      SUCESSO! O sistema esta rodando.
echo ===================================================
echo.
echo API disponivel em: http://localhost:8080
echo Swagger UI:        http://localhost:8080/swagger-ui.html
echo.
echo Pressione qualquer tecla para abrir a documentacao no navegador...
pause >nul

start http://localhost:8080/swagger-ui.html
