# AICademy

**An AI course marketplace. You tell it your role, experience, interests, and goals. A Spring Boot ranker returns six courses, and the React app can also re-rank the same catalog for a career path.**

![Java](https://img.shields.io/badge/Java-17-E76F00?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-6DB33F?logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-Vite-61DAFB?logo=react&logoColor=black)
![License](https://img.shields.io/badge/portfolio-project-lightgrey)

The catalog is curated in memory: named courses from Coursera, DeepLearning.AI, OpenAI, Anthropic, and the major cloud certifications, plus generated entries that point at real provider search pages. There is no account system and no trained model. The ranker is a weighted score, written out below.

The interface in this repo is the same UI as [AICademy-Frontend](https://github.com/danazzie/AICademy-Frontend). That repository adds the static file server used for a Railway deploy. The Railway URLs written in the previous README (`ai-cademy.up.railway.app` and `aicademy.up.railway.app`) returned 404 when checked in November 2026. Run it locally.

---

## Table of contents

- [What it does](#what-it-does)
- [How a course gets ranked](#how-a-course-gets-ranked)
- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Run it](#run-it)
- [API](#api)
- [Limits](#limits)
- [Русская версия](#русская-версия)

## What it does

1. **Browse.** The home page loads `GET /api/courses`. Cards show provider, level, rating, length, price, and tags. Search matches title, provider, tags, roles, and goals. Filters cover category and price (free, subscription, paid, exam fee).
2. **Personalize.** A form posts experience, job role, interests, and goals to `POST /api/courses/recommendations`. The response is a short explanation plus six courses. Those six replace the hero row.
3. **Pick a career path.** Eight paths (AI Engineer, Machine Learning Engineer, Data Scientist, NLP Engineer, AI Product Manager, MLOps Engineer, AI Researcher, AI Automation Specialist) re-rank the catalog in the browser and show six courses for that path.

Interests you can select: `llm`, `rag`, `mcp`, `nlp`, `ml`, `agents`, `fine-tuning`, `automation`, `evaluation`, `responsible ai`.

## How a course gets ranked

Two scores, on purpose. The server score is the personalized path. The browser score is the career-path path. They do not call each other.

### Server (`CourseService.recommend`)

Each course gets:

| Signal | Points |
|---|---|
| Each interest that matches a course tag | 5 |
| Each goal that matches a course goal | 4 |
| Job role contained in the course's role list | 4 |
| Experience band matches the course level | 3 |
| Course rating is 4.7 or higher | 1 |

"New" or "beginner" matches Beginner courses. "Advanced" or "expert" matches Intermediate and Advanced. Anything else matches Beginner and Intermediate. The top six win. A tie goes to the higher rating.

The form also sends `learningStyle` and `weeklyHours`. The API accepts them. `score` does not read them.

### Browser (career path)

For the selected path, each of the course's tags, roles, goals, title, subtitle, and category can add:

| Signal | Points |
|---|---|
| Text contains one of the path's skills | 4 |
| Text contains one of the path's roles | 3 |
| Text contains one of the path's goals | 3 |

Again the top six, with rating as the tie-break. This sort stays on the client so switching paths does not hit the API.

## Tech stack

| Piece | Choice | Why |
|---|---|---|
| API | Java 17, Spring Boot 3.3, validation starter | A small JSON API with a checked request body |
| Catalog | In-memory list built at startup | The data is curated content, not user data, so there is no database |
| UI | React, Vite, Lucide | One page: search, filters, the form, and the path picker |
| Run | Maven wrapper, `start.sh`, multi-stage Dockerfile | The container builds the jar and runs it on port 8080. The image is the API only |

CORS allows the frontend origin from `FRONTEND_URL` when that variable is set.

## Project structure

```
.
├── backend/
│   └── src/main/java/com/aicademy/
│       ├── controller/CourseController.java   # GET /api/courses, POST /api/courses/recommendations
│       ├── service/CourseService.java         # Catalog and the server score
│       ├── model/                             # Course, UserProfile, RecommendationResponse
│       └── config/CorsConfig.java
├── frontend/
│   └── src/main.jsx                           # UI, filters, career-path score
├── Dockerfile                                 # API image
└── start.sh                                   # Runs the API with the Maven wrapper
```

## Run it

You need JDK 17 and Node.js.

**API**

```bash
cd backend
./mvnw spring-boot:run
```

The API listens on `http://localhost:8080`.

**UI**

```bash
cd frontend
npm install
npm run dev
```

The UI listens on `http://localhost:5173` and calls `http://localhost:8080/api/courses` unless `VITE_API_URL` is set.

`./start.sh` from the repo root starts the API only.

### Recommendation body

```json
{
  "experience": "Some AI or data experience",
  "jobRole": "Software Engineer",
  "interests": ["llm", "rag", "mcp"],
  "goals": ["build ai apps", "ship production systems"],
  "learningStyle": "Project-based",
  "weeklyHours": 6
}
```

`experience` and `jobRole` are required. `learningStyle` and `weeklyHours` are stored on the request and do not change the six courses.

## API

| Method | Path | Returns |
|---|---|---|
| `GET` | `/api/courses` | The full catalog |
| `POST` | `/api/courses/recommendations` | `{ message, courses }` with six courses |

`GET /` is a short home response from `HomeController`, separate from the course API.

## Limits

The catalog is compiled into `CourseService`. Ratings, student counts, and prices are editorial figures for the cards, not a live pull from Coursera or the cloud vendors.

There are no accounts, saved paths, or reviews. Search is a substring match over the fields already on the card.

`learningStyle` and `weeklyHours` are the next inputs the score should use. They are collected so the form already has them.

The Dockerfile ships the API. The UI is a second process. [AICademy-Frontend](https://github.com/danazzie/AICademy-Frontend) is that UI with `server.js`, a small static server for a built `dist/` folder.

## Русская версия

**Витрина курсов по ИИ.** Каталог собран в памяти: известные курсы Coursera, DeepLearning.AI, OpenAI, Anthropic и облачные сертификации. Отдельной модели машинного обучения нет. Сервер считает вес по роли, уровню, интересам и целям и возвращает шесть курсов. В браузере те же курсы пересортируются под один из восьми карьерных треков.

Поля «стиль обучения» и «часы в неделю» форма отправляет, а ранжировщик их пока не учитывает. Ссылки на Railway из старого README в ноябре 2026 отвечали 404. Локальный запуск: `./mvnw spring-boot:run` в `backend` и `npm run dev` в `frontend`.
