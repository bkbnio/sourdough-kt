-- create "author" table
CREATE TABLE "author" ("id" uuid NOT NULL, "name" character varying(100) NOT NULL, "created_at" timestamp NOT NULL, "updated_at" timestamp NOT NULL, "version" integer NOT NULL DEFAULT 0, PRIMARY KEY ("id"));
-- create index "idx_author_name" to table: "author"
CREATE INDEX "idx_author_name" ON "author" ("name");
-- create "book" table
CREATE TABLE "book" ("id" uuid NOT NULL, "author_id" uuid NOT NULL, "isbn" character varying(100) NOT NULL, "title" character varying(100) NOT NULL, "price" double precision NOT NULL, "created_at" timestamp NOT NULL, "updated_at" timestamp NOT NULL, "version" integer NOT NULL DEFAULT 0, PRIMARY KEY ("id"), CONSTRAINT "book_author_id_fkey" FOREIGN KEY ("author_id") REFERENCES "author" ("id") ON UPDATE NO ACTION ON DELETE NO ACTION);
-- create index "idx_book_isbn" to table: "book"
CREATE UNIQUE INDEX "idx_book_isbn" ON "book" ("isbn");
