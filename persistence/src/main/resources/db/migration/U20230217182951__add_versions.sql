-- reverse: modify "book" table
ALTER TABLE "book" DROP COLUMN "version";
-- reverse: modify "author" table
ALTER TABLE "author" DROP COLUMN "version";
