-- liquibase formatted sql

-- changeset trkhasanov:1
CREATE TABLE notification_task (
id SERIAL PRIMARY KEY,
chat_id SERIAL,
message_text TEXT not null,
send_date TIMESTAMP not null
)