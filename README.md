# AICademy

AICademy is a Udemy-inspired learning marketplace focused on AI topics including LLMs, MCPs, NLP, machine learning, RAG, agents, fine-tuning, automation, and responsible AI.

## Tech stack

- Java 17
- Spring Boot 3
- React
- Vite
- Lucide React icons

## Features

- AI-focused course marketplace
- Curated course catalog served from a Java backend
- Personalized recommendations based on:
  - User experience level
  - Current job or role
  - Preferred AI topics
  - Career and learning goals
  - Learning style
  - Weekly study availability
- Modern responsive React interface
- Category filtering
- Udemy-style course cards with ratings, students, duration, tags, and pricing

## Run the backend

```bash
cd backend
mvn spring-boot:run
```

The backend runs at `http://localhost:8080`.

Available endpoints:

- `GET /api/courses`
- `POST /api/courses/recommendations`

## Run the frontend

```bash
cd frontend
npm install
npm run dev
```

The frontend runs at `http://localhost:5173`.

## Recommendation request example

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
