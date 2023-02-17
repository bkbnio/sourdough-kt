-- modify "author" table
ALTER TABLE "author" ADD COLUMN "version" integer NOT NULL DEFAULT 0;
-- modify "book" table
ALTER TABLE "book" ADD COLUMN "version" integer NOT NULL DEFAULT 0;
