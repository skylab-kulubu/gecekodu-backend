# Gece Kodu Web Servisi

<table align="center-left">
 <tr>
   <td><img src="https://github.com/omeraslanw/gecekodu-backend/blob/main/skylab%20logo.png?raw=true" alt="SKY LAB LOGO" width="200"/></td>
   <td><img src="https://github.com/omeraslanw/gecekodu-backend/blob/main/weblab%20logo.png?raw=true" alt="WEB LAB LOGO" width="200"/></td>
 </tr>
</table>


![Java](https://img.shields.io/badge/Java-17-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-brightgreen)
![Maven](https://img.shields.io/badge/Maven-3.9.6-red)

## Proje Hakkında

> Bu proje, SKY LAB: Bilgisayar Bilimleri Kulübü'nün her cuma düzenlediği Gece Kodu etkinliğinin katılımcılarını, etkinlik organizasyonunu ve yönetimini sağlamak amacıyla oluşturulmuştur.

## Proje Ekibi

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/omeraslanw">  
        <img src="https://avatars.githubusercontent.com/u/112866826?v=4" width="200px;" alt="Ömer Faruk Aslan"/>
        <br />
        <sub><b>Ömer Faruk Aslan</b></sub>
      </a>
      <a href="https://www.linkedin.com/in/%C3%B6mer-faruk-aslan-9392371b6/">
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/alperkyoruk">
        <img src="https://avatars.githubusercontent.com/u/116509257?v=4" width="200px;" alt="Alper Kaan Yörük"/>
        <br />
        <sub><b>Alper Kaan Yörük</b></sub>
      </a>
    </td>
  </tr>
</table> 

### Kullanılan Teknolojiler

- Java 17
- Spring Boot 3.3.5
- Spring Data JPA & Hibernate
- PostgreSQL
- Maven
- Lombok
- JWT 0.11.5

## Başlarken

### Ön Gereksinimler

* Java 17
* Maven
* PostgreSQL

### Kurulum ve Yapılandırma

1.  **Depoyu klonlayın:**
    ```sh
    git clone https://github.com/omeraslanw/gecekodu-backend.git
    ```
2.  **Veritabanını ayarlayın:**
    PostgreSQL'de `proje_db` adında bir veritabanı oluşturun.
3.  **Yapılandırma dosyasını düzenleyin:**
    `src/main/resources/application.properties` dosyasını aşağıdaki gibi kendi yerel ayarlarınızla güncelleyin:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/proje_db
    spring.datasource.username=postgres_kullanicisi
    spring.datasource.password=sifreniz
    spring.jpa.hibernate.ddl-auto=update
    ```

### Çalıştırma

1) **Bağımlılıkları yükleyin ve projeyi derleyin:**
```sh
mvn clean install
```

2) **Projeyi çalıştırmak için projenin kök dizininde aşağıdaki komutu çalıştırın:**
```sh
mvn spring-boot:run
```
