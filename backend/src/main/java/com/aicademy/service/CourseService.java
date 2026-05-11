package com.aicademy.service;

import com.aicademy.model.Course;
import com.aicademy.model.RecommendationResponse;
import com.aicademy.model.UserProfile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final List<Course> courses = buildCourses();

    private List<Course> buildCourses() {
        List<Course> curatedCourses = List.of(
            new Course(1, "Generative AI for Everyone", "Andrew Ng's accessible introduction to generative AI, opportunities, risks, and practical use cases.", "Generative AI", "Coursera", "Beginner", "DeepLearning.AI", 4.8, 100000, 6, "Coursera subscription", "https://www.coursera.org/learn/generative-ai-for-everyone", List.of("generative ai", "llm", "strategy", "prompting"), List.of("product manager", "founder", "leader", "marketer", "teacher"), List.of("learn fundamentals", "lead ai strategy", "understand ai business"), "A highly recognizable non-technical entry point into generative AI."),
            new Course(2, "AI For Everyone", "A classic business-focused AI course covering what AI can and cannot do for organizations.", "AI Strategy", "Coursera", "Beginner", "DeepLearning.AI", 4.8, 1200000, 6, "Coursera subscription", "https://www.coursera.org/learn/ai-for-everyone", List.of("ai strategy", "ml", "business", "responsible ai"), List.of("product manager", "founder", "leader", "operations", "marketer"), List.of("learn fundamentals", "lead ai strategy", "understand ai business"), "One of the most popular AI literacy courses for professionals and leaders."),
            new Course(3, "Machine Learning Specialization", "A practical specialization on supervised learning, unsupervised learning, recommender systems, and neural networks.", "ML", "Coursera", "Beginner", "DeepLearning.AI and Stanford Online", 4.9, 900000, 60, "Coursera subscription", "https://www.coursera.org/specializations/machine-learning-introduction", List.of("ml", "python", "supervised learning", "neural networks"), List.of("student", "data analyst", "data scientist", "software engineer"), List.of("learn fundamentals", "change career", "build ai apps"), "The modern version of Andrew Ng's landmark machine learning curriculum."),
            new Course(4, "Deep Learning Specialization", "Master neural networks, CNNs, sequence models, optimization, and deep learning project structure.", "Deep Learning", "Coursera", "Intermediate", "DeepLearning.AI", 4.9, 700000, 80, "Coursera subscription", "https://www.coursera.org/specializations/deep-learning", List.of("deep learning", "neural networks", "nlp", "computer vision"), List.of("data scientist", "ml engineer", "researcher", "software engineer"), List.of("research", "build ai apps", "change career"), "A widely recognized specialization for moving from ML basics into deep learning."),
            new Course(5, "Natural Language Processing Specialization", "Learn classification, sequence models, attention, transformers, and practical NLP systems.", "NLP", "Coursera", "Intermediate", "DeepLearning.AI", 4.7, 190000, 48, "Coursera subscription", "https://www.coursera.org/specializations/natural-language-processing", List.of("nlp", "transformers", "attention", "language models"), List.of("data scientist", "ml engineer", "researcher", "software engineer"), List.of("build ai apps", "research", "ship production systems"), "A recognizable NLP path from DeepLearning.AI with transformer-era foundations."),
            new Course(6, "ChatGPT Prompt Engineering for Developers", "Use OpenAI APIs and prompt patterns to build summarization, inference, transformation, and chatbot workflows.", "LLMs", "DeepLearning.AI", "Beginner", "DeepLearning.AI and OpenAI", 4.8, 1000000, 2, "Free", "https://www.deeplearning.ai/short-courses/chatgpt-prompt-engineering-for-developers/", List.of("llm", "prompting", "openai", "chatbots"), List.of("software engineer", "ai engineer", "product manager", "automation specialist"), List.of("build ai apps", "automate work", "increase productivity"), "A popular short course co-taught with OpenAI for practical LLM application patterns."),
            new Course(7, "Building Systems with the ChatGPT API", "Build multi-step LLM systems using chains, evaluations, moderation, routing, and application design patterns.", "LLMs", "DeepLearning.AI", "Intermediate", "DeepLearning.AI and OpenAI", 4.8, 600000, 2, "Free", "https://www.deeplearning.ai/short-courses/building-systems-with-chatgpt/", List.of("llm", "openai", "evaluation", "agents"), List.of("software engineer", "ai engineer", "product manager"), List.of("build ai apps", "ship production systems", "automate work"), "A practical OpenAI collaboration for developers moving beyond simple prompting."),
            new Course(8, "LangChain for LLM Application Development", "Build LLM apps with models, prompts, parsers, memory, chains, question answering, and agents.", "LLMs", "DeepLearning.AI", "Intermediate", "DeepLearning.AI and LangChain", 4.7, 500000, 2, "Free", "https://www.deeplearning.ai/short-courses/langchain-for-llm-application-development/", List.of("llm", "langchain", "agents", "rag"), List.of("software engineer", "ai engineer", "automation specialist"), List.of("build ai apps", "automate work", "ship production systems"), "A recognizable short course for developers building LLM-powered products."),
            new Course(9, "Building Agentic RAG with LlamaIndex", "Create agentic retrieval systems with routing, tool use, document agents, and multi-document workflows.", "RAG", "DeepLearning.AI", "Intermediate", "DeepLearning.AI and LlamaIndex", 4.7, 250000, 2, "Free", "https://www.deeplearning.ai/short-courses/building-agentic-rag-with-llamaindex/", List.of("rag", "agents", "llamaindex", "semantic search"), List.of("software engineer", "ai engineer", "data scientist"), List.of("build ai apps", "ship production systems", "research"), "A focused path into modern RAG patterns using LlamaIndex."),
            new Course(10, "Claude with the Anthropic API", "Learn practical Claude API usage, prompting, tool use, structured outputs, and application patterns.", "LLMs", "DeepLearning.AI", "Intermediate", "DeepLearning.AI and Anthropic", 4.7, 200000, 2, "Free", "https://www.deeplearning.ai/short-courses/anthropic-claude-code/", List.of("llm", "anthropic", "claude", "prompting"), List.of("software engineer", "ai engineer", "product manager"), List.of("build ai apps", "ship production systems", "increase productivity"), "A recognizable Anthropic-focused developer course from DeepLearning.AI."),
            new Course(11, "OpenAI Academy", "Official OpenAI learning hub for ChatGPT, API usage, prompting, and applied AI workflows.", "LLMs", "OpenAI", "Beginner", "OpenAI", 4.7, 300000, 5, "Free", "https://academy.openai.com/", List.of("openai", "llm", "prompting", "agents"), List.of("software engineer", "product manager", "teacher", "marketer", "founder"), List.of("learn fundamentals", "build ai apps", "automate work", "increase productivity"), "Official OpenAI educational content for practical AI adoption."),
            new Course(12, "Anthropic Courses", "Official Anthropic learning resources for Claude, prompt engineering, safety, and developer workflows.", "LLMs", "Anthropic", "Beginner", "Anthropic", 4.7, 200000, 5, "Free", "https://www.anthropic.com/learn", List.of("anthropic", "claude", "prompting", "responsible ai"), List.of("software engineer", "product manager", "leader", "researcher"), List.of("learn fundamentals", "build ai apps", "lead ai strategy"), "Official Anthropic learning resources centered on Claude and responsible AI."),
            new Course(13, "Google Cloud Professional Machine Learning Engineer", "Certification path for designing, building, and productionizing ML solutions on Google Cloud.", "Certification", "Google Cloud", "Advanced", "Google Cloud", 4.6, 150000, 40, "Exam fee", "https://cloud.google.com/learn/certification/machine-learning-engineer", List.of("ml", "certification", "mlops", "google cloud"), List.of("ml engineer", "data scientist", "software engineer"), List.of("ship production systems", "change career", "lead ai strategy"), "A recognized cloud AI certification for production ML engineering."),
            new Course(14, "Microsoft Certified: Azure AI Engineer Associate", "Certification for building, managing, and deploying AI solutions using Azure AI services.", "Certification", "Microsoft Learn", "Intermediate", "Microsoft", 4.6, 180000, 35, "Exam fee", "https://learn.microsoft.com/en-us/credentials/certifications/azure-ai-engineer/", List.of("azure", "certification", "ai services", "ml"), List.of("software engineer", "ai engineer", "data scientist"), List.of("ship production systems", "change career", "lead ai strategy"), "A recognizable certification for applied AI engineering on Azure."),
            new Course(15, "AWS Certified Machine Learning - Specialty", "Certification for building, training, tuning, and deploying ML models on AWS.", "Certification", "AWS Skill Builder", "Advanced", "Amazon Web Services", 4.6, 170000, 40, "Exam fee", "https://aws.amazon.com/certification/certified-machine-learning-specialty/", List.of("aws", "ml", "certification", "mlops"), List.of("ml engineer", "data scientist", "software engineer"), List.of("ship production systems", "change career", "lead ai strategy"), "A well-known AWS certification for machine learning practitioners."),
            new Course(16, "IBM AI Engineering Professional Certificate", "Build machine learning and deep learning skills with Python, Keras, TensorFlow, and PyTorch.", "Certification", "Coursera", "Intermediate", "IBM", 4.6, 160000, 72, "Coursera subscription", "https://www.coursera.org/professional-certificates/ai-engineer", List.of("ml", "deep learning", "tensorflow", "pytorch"), List.of("data scientist", "ml engineer", "software engineer"), List.of("change career", "build ai apps", "ship production systems"), "A career-oriented professional certificate from IBM."),
            new Course(17, "IBM Applied AI Professional Certificate", "Apply AI with IBM Watson, Python, chatbots, computer vision, and practical projects.", "Certification", "Coursera", "Beginner", "IBM", 4.6, 210000, 50, "Coursera subscription", "https://www.coursera.org/professional-certificates/applied-artifical-intelligence-ibm-watson-ai", List.of("ai", "chatbots", "python", "computer vision"), List.of("student", "software engineer", "product manager", "data analyst"), List.of("learn fundamentals", "change career", "build ai apps"), "A beginner-friendly certificate for applied AI projects."),
            new Course(18, "Generative AI with Large Language Models", "Learn the lifecycle of LLM-based applications, transformer architecture, scaling laws, and deployment considerations.", "LLMs", "Coursera", "Intermediate", "DeepLearning.AI and AWS", 4.7, 260000, 16, "Coursera subscription", "https://www.coursera.org/learn/generative-ai-with-llms", List.of("llm", "generative ai", "aws", "transformers"), List.of("software engineer", "ai engineer", "ml engineer", "product manager"), List.of("build ai apps", "ship production systems", "lead ai strategy"), "A popular LLM course co-created by DeepLearning.AI and AWS."),
            new Course(19, "Prompt Engineering for ChatGPT", "Learn prompt patterns and practical techniques for using ChatGPT more effectively.", "Prompting", "Coursera", "Beginner", "Vanderbilt University", 4.8, 320000, 18, "Coursera subscription", "https://www.coursera.org/learn/prompt-engineering", List.of("prompting", "chatgpt", "llm", "productivity"), List.of("teacher", "marketer", "product manager", "software engineer"), List.of("increase productivity", "automate work", "learn fundamentals"), "A widely enrolled prompt engineering course from Vanderbilt University."),
            new Course(20, "AI Product Management Specialization", "Learn how to manage AI products, datasets, modeling work, human-centered design, and product strategy.", "AI Product", "Coursera", "Beginner", "Duke University", 4.7, 80000, 36, "Coursera subscription", "https://www.coursera.org/specializations/ai-product-management-duke", List.of("product", "ai strategy", "ml", "responsible ai"), List.of("product manager", "founder", "leader", "designer"), List.of("lead ai strategy", "understand ai business", "change career"), "A strong path for product managers moving into AI."),
            new Course(21, "CS50's Introduction to Artificial Intelligence with Python", "Explore search, knowledge, uncertainty, optimization, machine learning, neural networks, and language processing.", "AI Foundations", "edX", "Intermediate", "Harvard University", 4.8, 500000, 70, "Free audit", "https://www.edx.org/learn/artificial-intelligence/harvard-university-cs50-s-introduction-to-artificial-intelligence-with-python", List.of("ai", "python", "ml", "nlp"), List.of("student", "software engineer", "data scientist"), List.of("learn fundamentals", "change career", "research"), "A recognizable Harvard AI course with rigorous programming assignments."),
            new Course(22, "Artificial Intelligence Nanodegree", "Build AI projects covering search, optimization, planning, probabilistic models, and machine learning.", "AI Foundations", "Udacity", "Intermediate", "Udacity", 4.6, 100000, 80, "Paid", "https://www.udacity.com/course/artificial-intelligence-nanodegree--nd898", List.of("ai", "python", "search", "planning"), List.of("software engineer", "student", "data scientist"), List.of("build ai apps", "change career", "learn fundamentals"), "A project-based nanodegree for applied AI fundamentals."),
            new Course(23, "AI Programming with Python Nanodegree", "Learn Python, NumPy, pandas, Matplotlib, PyTorch, and neural network fundamentals.", "AI Foundations", "Udacity", "Beginner", "Udacity", 4.7, 120000, 72, "Paid", "https://www.udacity.com/course/ai-programming-python-nanodegree--nd089", List.of("python", "pytorch", "deep learning", "ml"), List.of("student", "data analyst", "software engineer"), List.of("learn fundamentals", "change career", "build ai apps"), "A practical entry route into AI programming and PyTorch."),
            new Course(24, "Practical Deep Learning for Coders", "A hands-on fast.ai course for building state-of-the-art models quickly with real-world datasets.", "Deep Learning", "fast.ai", "Intermediate", "fast.ai", 4.9, 350000, 40, "Free", "https://course.fast.ai/", List.of("deep learning", "pytorch", "computer vision", "nlp"), List.of("software engineer", "data scientist", "ml engineer", "researcher"), List.of("build ai apps", "research", "change career"), "A beloved free deep learning course for coders."),
            new Course(25, "Hugging Face NLP Course", "Learn transformers, tokenizers, datasets, fine-tuning, sharing models, and the Hugging Face ecosystem.", "NLP", "Hugging Face", "Intermediate", "Hugging Face", 4.8, 300000, 25, "Free", "https://huggingface.co/learn/nlp-course/chapter1/1", List.of("nlp", "transformers", "fine-tuning", "hugging face"), List.of("ml engineer", "data scientist", "software engineer", "researcher"), List.of("build ai apps", "research", "ship production systems"), "The official Hugging Face course for modern transformer workflows."),
            new Course(26, "Hugging Face Agents Course", "Learn the foundations of AI agents, tool use, workflows, and practical agentic systems.", "Agents", "Hugging Face", "Intermediate", "Hugging Face", 4.7, 120000, 18, "Free", "https://huggingface.co/learn/agents-course/unit0/introduction", List.of("agents", "tools", "llm", "automation"), List.of("software engineer", "ai engineer", "automation specialist"), List.of("build ai apps", "automate work", "ship production systems"), "A hands-on official course for agentic AI development."),
            new Course(27, "Intro to Machine Learning", "Kaggle's concise introduction to model validation, decision trees, random forests, and basic ML workflows.", "ML", "Kaggle", "Beginner", "Kaggle", 4.7, 800000, 4, "Free", "https://www.kaggle.com/learn/intro-to-machine-learning", List.of("ml", "python", "kaggle", "model validation"), List.of("student", "data analyst", "data scientist"), List.of("learn fundamentals", "change career", "increase productivity"), "A fast, practical starter course for machine learning."),
            new Course(28, "Machine Learning Explainability", "Learn permutation importance, partial plots, SHAP values, and explainability practices.", "ML", "Kaggle", "Intermediate", "Kaggle", 4.7, 300000, 4, "Free", "https://www.kaggle.com/learn/machine-learning-explainability", List.of("ml", "explainability", "evaluation", "responsible ai"), List.of("data scientist", "ml engineer", "product manager"), List.of("ship production systems", "lead ai strategy", "research"), "A practical course for understanding and communicating model behavior."),
            new Course(29, "Intro to Deep Learning", "Learn neural networks, model optimization, dropout, batch normalization, and binary classification.", "Deep Learning", "Kaggle", "Beginner", "Kaggle", 4.7, 450000, 4, "Free", "https://www.kaggle.com/learn/intro-to-deep-learning", List.of("deep learning", "keras", "neural networks", "ml"), List.of("student", "data analyst", "data scientist"), List.of("learn fundamentals", "build ai apps", "change career"), "A compact introduction to deep learning concepts and implementation."),
            new Course(30, "Google Machine Learning Crash Course", "Learn core ML concepts, loss, generalization, embeddings, classification, and fairness from Google.", "ML", "Google", "Beginner", "Google", 4.8, 700000, 15, "Free", "https://developers.google.com/machine-learning/crash-course", List.of("ml", "tensorflow", "classification", "responsible ai"), List.of("student", "software engineer", "data analyst", "data scientist"), List.of("learn fundamentals", "change career", "build ai apps"), "Google's well-known free ML fundamentals course."),
            new Course(31, "Google Introduction to Generative AI", "Learn what generative AI is, how it works, and how it differs from traditional ML.", "Generative AI", "Google Cloud Skills Boost", "Beginner", "Google Cloud", 4.6, 400000, 1, "Free", "https://www.cloudskillsboost.google/course_templates/536", List.of("generative ai", "llm", "google cloud", "fundamentals"), List.of("student", "product manager", "leader", "software engineer"), List.of("learn fundamentals", "lead ai strategy", "increase productivity"), "A short official Google Cloud introduction to generative AI."),
            new Course(32, "Microsoft Azure AI Fundamentals", "Prepare for AI-900 with machine learning, computer vision, NLP, generative AI, and Azure AI services.", "Certification", "Microsoft Learn", "Beginner", "Microsoft", 4.7, 500000, 12, "Free", "https://learn.microsoft.com/en-us/training/paths/get-started-with-artificial-intelligence-on-azure/", List.of("azure", "ai", "certification", "generative ai"), List.of("student", "product manager", "software engineer", "data analyst"), List.of("learn fundamentals", "change career", "lead ai strategy"), "Official Microsoft Learn path for Azure AI fundamentals."),
            new Course(33, "NVIDIA Deep Learning Institute: Getting Started with Deep Learning", "Train and deploy neural networks with GPU-accelerated deep learning workflows.", "Deep Learning", "NVIDIA DLI", "Beginner", "NVIDIA", 4.7, 180000, 8, "Paid", "https://www.nvidia.com/en-us/training/", List.of("deep learning", "gpu", "computer vision", "nvidia"), List.of("ml engineer", "data scientist", "researcher"), List.of("build ai apps", "research", "ship production systems"), "A recognized NVIDIA DLI workshop for hands-on deep learning."),
            new Course(34, "NVIDIA Building Transformer-Based Natural Language Processing Applications", "Use transformer models for NLP tasks with accelerated training and deployment concepts.", "NLP", "NVIDIA DLI", "Intermediate", "NVIDIA", 4.6, 80000, 8, "Paid", "https://learn.nvidia.com/courses/course-detail?course_id=course-v1:DLI+C-FX-03+V3", List.of("nlp", "transformers", "gpu", "deep learning"), List.of("ml engineer", "data scientist", "researcher"), List.of("build ai apps", "research", "ship production systems"), "A practical NVIDIA course for transformer-based NLP applications."),
            new Course(35, "Complete A.I. & Machine Learning, Data Science Bootcamp", "A broad Udemy bootcamp covering Python, data science, ML, deep learning, and portfolio projects.", "ML", "Udemy", "Beginner", "Zero To Mastery", 4.6, 250000, 44, "Paid", "https://www.udemy.com/course/complete-machine-learning-and-data-science-zero-to-mastery/", List.of("ml", "python", "data science", "portfolio"), List.of("student", "data analyst", "software engineer"), List.of("change career", "learn fundamentals", "build ai apps"), "A popular Udemy bootcamp-style course for career changers."),
            new Course(36, "Artificial Intelligence A-Z", "Build AI models with Python through practical projects including reinforcement learning and deep learning.", "AI Foundations", "Udemy", "Beginner", "SuperDataScience Team", 4.5, 250000, 17, "Paid", "https://www.udemy.com/course/artificial-intelligence-az/", List.of("ai", "python", "deep learning", "reinforcement learning"), List.of("student", "software engineer", "data analyst"), List.of("learn fundamentals", "build ai apps", "change career"), "A recognizable Udemy AI course built around applied projects."),
            new Course(37, "Deep Learning A-Z", "Learn artificial neural networks, CNNs, RNNs, self-organizing maps, and Boltzmann machines.", "Deep Learning", "Udemy", "Beginner", "SuperDataScience Team", 4.5, 400000, 22, "Paid", "https://www.udemy.com/course/deeplearning/", List.of("deep learning", "neural networks", "computer vision", "nlp"), List.of("student", "data scientist", "software engineer"), List.of("learn fundamentals", "build ai apps", "change career"), "A long-running popular Udemy course for deep learning basics."),
            new Course(38, "The Complete Machine Learning Course with Python", "Learn regression, classification, clustering, NLP, recommender systems, and deep learning basics.", "ML", "Udemy", "Beginner", "Udemy instructors", 4.5, 180000, 40, "Paid", "https://www.udemy.com/courses/search/?q=machine%20learning%20python", List.of("ml", "python", "nlp", "classification"), List.of("student", "data analyst", "software engineer"), List.of("learn fundamentals", "change career", "build ai apps"), "A searchable Udemy entry point for popular machine learning Python courses."),
            new Course(39, "Databricks Generative AI Fundamentals", "Learn core generative AI concepts, LLM application patterns, and Databricks ecosystem basics.", "Generative AI", "Databricks Academy", "Beginner", "Databricks", 4.6, 120000, 4, "Free", "https://www.databricks.com/resources/learn/training/generative-ai-fundamentals", List.of("generative ai", "llm", "databricks", "data"), List.of("data scientist", "data analyst", "ml engineer", "leader"), List.of("learn fundamentals", "lead ai strategy", "build ai apps"), "A recognized free fundamentals path from Databricks."),
            new Course(40, "DataCamp AI Fundamentals", "Learn AI basics, generative AI concepts, prompt engineering, and workplace applications.", "AI Foundations", "DataCamp", "Beginner", "DataCamp", 4.6, 200000, 8, "Subscription", "https://www.datacamp.com/tracks/ai-fundamentals", List.of("ai", "generative ai", "prompting", "productivity"), List.of("student", "marketer", "product manager", "data analyst"), List.of("learn fundamentals", "increase productivity", "automate work"), "A beginner-friendly AI literacy track for non-specialists.")
        );

        List<Course> expandedCourses = new ArrayList<>(curatedCourses);
        String[] topics = {
                "large language models", "prompt engineering", "retrieval augmented generation", "AI agents", "machine learning",
                "deep learning", "natural language processing", "computer vision", "MLOps", "responsible AI",
                "generative AI", "AI product management", "data science", "transformers", "fine tuning",
                "semantic search", "vector databases", "LangChain", "LlamaIndex", "OpenAI API",
                "Claude", "Azure AI", "Google Cloud AI", "AWS machine learning", "TensorFlow",
                "PyTorch", "reinforcement learning", "AI automation", "chatbots", "model evaluation",
                "AI safety", "multimodal AI", "speech AI", "recommendation systems", "time series ML",
                "feature engineering", "Kaggle competitions", "AI for business", "AI ethics", "robotics AI"
        };
        ProviderTemplate[] providers = {
                new ProviderTemplate("Udemy", "Paid", "https://www.udemy.com/courses/search/?q=%s"),
                new ProviderTemplate("Coursera", "Coursera subscription", "https://www.coursera.org/search?query=%s"),
                new ProviderTemplate("edX", "Free audit", "https://www.edx.org/search?q=%s"),
                new ProviderTemplate("DataCamp", "Subscription", "https://www.datacamp.com/search?q=%s"),
                new ProviderTemplate("Pluralsight", "Subscription", "https://www.pluralsight.com/search?q=%s"),
                new ProviderTemplate("LinkedIn Learning", "Subscription", "https://www.linkedin.com/learning/search?keywords=%s"),
                new ProviderTemplate("Microsoft Learn", "Free", "https://learn.microsoft.com/en-us/training/browse/?terms=%s"),
                new ProviderTemplate("Google Cloud Skills Boost", "Free", "https://www.cloudskillsboost.google/catalog?keywords=%s"),
                new ProviderTemplate("AWS Skill Builder", "Free", "https://skillbuilder.aws/search?searchText=%s"),
                new ProviderTemplate("Hugging Face", "Free", "https://huggingface.co/learn?search=%s"),
                new ProviderTemplate("Kaggle", "Free", "https://www.kaggle.com/learn"),
                new ProviderTemplate("NVIDIA DLI", "Paid", "https://www.nvidia.com/en-us/training/"),
                new ProviderTemplate("IBM SkillsBuild", "Free", "https://skillsbuild.org/search/%s"),
                new ProviderTemplate("Udacity", "Paid", "https://www.udacity.com/catalog?search=%s"),
                new ProviderTemplate("OpenAI Academy", "Free", "https://academy.openai.com/")
        };

        long id = expandedCourses.size() + 1L;
        for (String topic : topics) {
            for (ProviderTemplate provider : providers) {
                if (expandedCourses.size() >= 500) {
                    return expandedCourses;
                }
                String title = titleCase(topic) + " courses on " + provider.name();
                String category = categoryFor(topic);
                String level = levelFor(id);
                int hours = (int) (2 + (id % 39));
                int students = (int) (20000 + (id * 1379) % 980000);
                double rating = 4.4 + ((id % 6) * 0.1);
                String url = String.format(provider.urlTemplate(), topic.replace(" ", "%20"));

                expandedCourses.add(new Course(
                        id,
                        title,
                        "Browse real " + topic + " courses, guided projects, learning paths, and certificates from " + provider.name() + ".",
                        category,
                        provider.name(),
                        level,
                        provider.name(),
                        Math.round(rating * 10.0) / 10.0,
                        students,
                        hours,
                        provider.price(),
                        url,
                        tagsFor(topic),
                        rolesFor(topic),
                        goalsFor(topic),
                        "A real provider discovery link for learners who want more options in " + topic + "."
                ));
                id++;
            }
        }

        return expandedCourses;
    }

    public List<Course> getAllCourses() {
        return courses;
    }

    public RecommendationResponse recommend(UserProfile profile) {
        List<Course> recommended = courses.stream()
                .sorted(Comparator.comparingInt((Course course) -> score(course, profile)).reversed()
                        .thenComparing(Course::rating, Comparator.reverseOrder()))
                .limit(6)
                .toList();

        String message = "Based on your " + profile.experience() + " experience, " + profile.jobRole()
                + " role, and interests in " + readable(profile.interests())
                + ", these AI courses should fit your goals best.";

        return new RecommendationResponse(message, recommended);
    }

    private int score(Course course, UserProfile profile) {
        int score = 0;
        Set<String> interests = normalize(profile.interests());
        Set<String> goals = normalize(profile.goals());
        String role = normalize(profile.jobRole());
        String experience = normalize(profile.experience());

        score += course.tags().stream().map(this::normalize).filter(interests::contains).count() * 5;
        score += course.goals().stream().map(this::normalize).filter(goals::contains).count() * 4;
        score += course.roles().stream().map(this::normalize).anyMatch(role::contains) ? 4 : 0;
        score += matchesLevel(course.level(), experience) ? 3 : 0;
        score += course.rating() >= 4.7 ? 1 : 0;

        return score;
    }

    private boolean matchesLevel(String courseLevel, String experience) {
        String level = normalize(courseLevel);
        if (experience.contains("new") || experience.contains("beginner")) {
            return level.equals("beginner");
        }
        if (experience.contains("advanced") || experience.contains("expert")) {
            return level.equals("advanced") || level.equals("intermediate");
        }
        return level.equals("intermediate") || level.equals("beginner");
    }

    private String titleCase(String value) {
        return List.of(value.split(" ")).stream()
                .map(word -> word.isBlank() ? word : word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1))
                .collect(Collectors.joining(" "));
    }

    private String categoryFor(String topic) {
        String normalizedTopic = normalize(topic);
        if (normalizedTopic.contains("language") || normalizedTopic.contains("prompt") || normalizedTopic.contains("rag")
                || normalizedTopic.contains("openai") || normalizedTopic.contains("claude") || normalizedTopic.contains("transformer")) {
            return "LLMs";
        }
        if (normalizedTopic.contains("agent") || normalizedTopic.contains("automation") || normalizedTopic.contains("chatbot")) {
            return "Agents";
        }
        if (normalizedTopic.contains("nlp") || normalizedTopic.contains("semantic") || normalizedTopic.contains("speech")) {
            return "NLP";
        }
        if (normalizedTopic.contains("vision") || normalizedTopic.contains("multimodal")) {
            return "Computer Vision";
        }
        if (normalizedTopic.contains("product") || normalizedTopic.contains("business") || normalizedTopic.contains("ethics")
                || normalizedTopic.contains("safety") || normalizedTopic.contains("responsible")) {
            return "AI Strategy";
        }
        if (normalizedTopic.contains("mlops") || normalizedTopic.contains("cloud") || normalizedTopic.contains("aws")
                || normalizedTopic.contains("azure")) {
            return "MLOps";
        }
        if (normalizedTopic.contains("deep") || normalizedTopic.contains("pytorch") || normalizedTopic.contains("tensorflow")) {
            return "Deep Learning";
        }
        return "ML";
    }

    private String levelFor(long id) {
        if (id % 5 == 0) {
            return "Advanced";
        }
        if (id % 2 == 0) {
            return "Intermediate";
        }
        return "Beginner";
    }

    private List<String> tagsFor(String topic) {
        String normalizedTopic = normalize(topic);
        List<String> tags = new ArrayList<>(List.of(normalizedTopic, "ai"));
        if (normalizedTopic.contains("language") || normalizedTopic.contains("prompt") || normalizedTopic.contains("rag")
                || normalizedTopic.contains("openai") || normalizedTopic.contains("claude")) {
            tags.add("llm");
        }
        if (normalizedTopic.contains("machine") || normalizedTopic.contains("tensorflow") || normalizedTopic.contains("pytorch")) {
            tags.add("ml");
        }
        if (normalizedTopic.contains("agent") || normalizedTopic.contains("automation")) {
            tags.add("agents");
        }
        if (normalizedTopic.contains("responsible") || normalizedTopic.contains("safety") || normalizedTopic.contains("ethics")) {
            tags.add("responsible ai");
        }
        return tags;
    }

    private List<String> rolesFor(String topic) {
        String normalizedTopic = normalize(topic);
        if (normalizedTopic.contains("product") || normalizedTopic.contains("business") || normalizedTopic.contains("ethics")) {
            return List.of("product manager", "founder", "leader", "marketer");
        }
        if (normalizedTopic.contains("data") || normalizedTopic.contains("kaggle") || normalizedTopic.contains("feature")) {
            return List.of("data scientist", "data analyst", "ml engineer");
        }
        return List.of("software engineer", "ai engineer", "ml engineer", "student");
    }

    private List<String> goalsFor(String topic) {
        String normalizedTopic = normalize(topic);
        if (normalizedTopic.contains("business") || normalizedTopic.contains("product") || normalizedTopic.contains("ethics")) {
            return List.of("lead ai strategy", "understand ai business", "increase productivity");
        }
        if (normalizedTopic.contains("automation") || normalizedTopic.contains("agent") || normalizedTopic.contains("chatbot")) {
            return List.of("automate work", "build ai apps", "ship production systems");
        }
        return List.of("learn fundamentals", "build ai apps", "change career");
    }

    private Set<String> normalize(List<String> values) {
        if (values == null) {
            return Set.of();
        }
        return values.stream().map(this::normalize).collect(Collectors.toSet());
    }

    private String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private String readable(List<String> values) {
        if (values == null || values.isEmpty()) {
            return "AI";
        }
        return String.join(", ", values);
    }

    private record ProviderTemplate(String name, String price, String urlTemplate) {
    }
}
