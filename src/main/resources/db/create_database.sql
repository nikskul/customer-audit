-- *
-- Execute as cluster superuser
-- *

-- Create application role
CREATE ROLE application LOGIN PASSWORD 'postgres';
REVOKE ALL ON SCHEMA public FROM application;

-- Create application db
CREATE DATABASE customer_audit OWNER application;
\c customer_audit application
CREATE SCHEMA application AUTHORIZATION application;

-- Grant privileges
GRANT ALL ON SCHEMA application TO application;
