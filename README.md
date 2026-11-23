# Gym Management System (Spring Boot MVC + Thymeleaf + H2/MySQL)

Full-stack Spring Boot MVC web application for fitness center operations, built with Spring Data JPA, Thymeleaf, Chart.js, and Bootstrap 5.

## Features
- **Operations Dashboard (`/`)**: Live member count, total subscription revenue, most popular plan, Chart.js membership distribution chart, class occupancy bars, and recent registrations with member photos.
- **Members Directory (`/members`)**: Paginated searchable member roster with profile photos, plan assignments, and detail views.
- **Membership Plans (`/plans`)**: CRUD management for monthly/quarterly/annual tiers and pricing.
- **Coaching Staff (`/trainers`)**: Trainer directory with specialty tracking and contact info.
- **Workout Classes (`/classes`)**: Group session scheduling and studio capacity management.
- **Zero-Setup H2 Startup**: Boots out of the box with an in-memory H2 database and automatic sample data (`DataInitializer`), plus an optional `mysql` Spring profile.

## Project Structure
```
Gym-Management-SpringBoot-MVC/
├── gymmanagement/              # Spring Boot MVC application (Maven, Java 17)
│   ├── src/main/java/          # Entities, repositories, services, controllers, DataInitializer
│   ├── src/main/resources/     # Thymeleaf templates, gym-theme.css, H2 & MySQL configs
│   └── uploads/                # Member profile photos served via /uploads/**
└── docs/
    ├── mini_projet_spring_boot.pdf
    ├── sample-photos/
    └── screenshots/
```

## Quick Start

```bash
cd gymmanagement
mvn spring-boot:run
```

Open [http://localhost:8081](http://localhost:8081).

To run with an external MySQL database instead of H2:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
