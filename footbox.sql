-- =========================================
-- FOODBOX DATABASE SCHEMA (PostgreSQL)
-- Letterboxd-like for food
-- =========================================

-- ---------- EXTENSIONS ----------
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ---------- USERS ----------
CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username VARCHAR(30) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    avatar_url TEXT,
    created_at TIMESTAMP DEFAULT now()
);

-- ---------- PLACES ----------
CREATE TABLE places (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL,
    type VARCHAR(30), -- restaurant, home, street_food
    city VARCHAR(100),
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6),
    created_at TIMESTAMP DEFAULT now()
);

-- ---------- DISHES (GLOBAL OBJECT) ----------
CREATE TABLE dishes (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(150) NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT now(),
    UNIQUE (name)
);

-- ---------- MEALS (USER JOURNAL) ----------
CREATE TABLE meals (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    dish_id UUID NOT NULL REFERENCES dishes(id) ON DELETE CASCADE,
    place_id UUID REFERENCES places(id) ON DELETE SET NULL,
    eaten_at TIMESTAMP DEFAULT now(),
    UNIQUE (user_id, dish_id, eaten_at)
);

-- ---------- REVIEWS ----------
CREATE TABLE reviews (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    meal_id UUID UNIQUE REFERENCES meals(id) ON DELETE CASCADE,
    rating DECIMAL(2,1) CHECK (rating BETWEEN 0 AND 5),
    comment TEXT,
    mood VARCHAR(30),
    created_at TIMESTAMP DEFAULT now()
);

-- ---------- TAGS ----------
CREATE TABLE tags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- ---------- DISH <-> TAGS ----------
CREATE TABLE dish_tags (
    dish_id UUID REFERENCES dishes(id) ON DELETE CASCADE,
    tag_id INT REFERENCES tags(id) ON DELETE CASCADE,
    PRIMARY KEY (dish_id, tag_id)
);

-- ---------- INDEXES ----------
CREATE INDEX idx_meals_user ON meals(user_id);
CREATE INDEX idx_meals_dish ON meals(dish_id);
CREATE INDEX idx_reviews_rating ON reviews(rating);
CREATE INDEX idx_places_city ON places(city);
CREATE INDEX idx_dish_tags_tag ON dish_tags(tag_id);

-- ---------- VIEWS ----------

-- Average rating per dish
CREATE VIEW dish_ratings AS
SELECT
    d.id AS dish_id,
    d.name,
    ROUND(AVG(r.rating), 2) AS avg_rating,
    COUNT(r.id) AS total_reviews
FROM dishes d
JOIN meals m ON m.dish_id = d.id
JOIN reviews r ON r.meal_id = m.id
GROUP BY d.id;

-- User activity history
CREATE VIEW user_meals AS
SELECT
    u.username,
    d.name AS dish,
    p.name AS place,
    r.rating,
    r.comment,
    m.eaten_at
FROM users u
JOIN meals m ON m.user_id = u.id
JOIN dishes d ON d.id = m.dish_id
LEFT JOIN places p ON p.id = m.place_id
LEFT JOIN reviews r ON r.meal_id = m.id;

-- =========================================
-- END OF SCHEMA
-- =========================================
