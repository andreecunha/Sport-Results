# Sport-Results

**Sport-Results** is a platform for viewing real-time sports results. It allows users to consult ongoing games and receive live updates. Built using Java Spring Boot and Thymeleaf, it integrates a backend data layer with a responsive web interface.

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Key Features](#key-features)
3. [System Architecture](#system-architecture)
4. [Installation Guide](#installation-guide)

---

## Project Overview

The platform enables users to:
- View and track live game results.
- Submit game events (goals, cards, etc.).
- Administrators can manage teams, players, and games.

The backend communicates with an SQL database using JPA for data persistence and integrates with an external sports API (api-sports.io) for fetching real-world sports data.

---

## Key Features

1. **User Functionality:**
   - Register/login and submit game events.
   - View game details, including real-time scores and event history.
   - Access statistics for players and teams.

2. **Administrator Functionality:**
   - Manage users, teams, players, games, and events.
   - Approve or reject submitted game events.
   - View and update detailed game and player statistics.

3. **Real-Time Updates:**
   - Automatic result updates based on submitted game events.
   - Use of Spring Boot and Thymeleaf for a responsive web experience.

---

## System Architecture

The system follows a three-layer architecture:
1. **Controllers:** Handle HTTP requests from users and administrators.
2. **Services:** Contain business logic for data manipulation.
3. **Repositories:** Interface with the database to perform CRUD operations.

The platform includes:
- **Frontend:** Built with Thymeleaf and Bootstrap for responsive design.
- **Backend:** Developed in Java with Spring Boot and integrated with PostgreSQL.
- **Database:** Stores users, teams, games, events, and statistics.

---

## Installation Guide

1. Clone the repository:
   ```bash
   git clone https://github.com/andreecunha/Sport-Results.git
