schema "public" {}

table "book" {
  schema = schema.public
  column "id" {
    null = false
    type = uuid
  }

  column "author_id" {
    null = false
    type = uuid
  }

  column "isbn" {
    null = false
    type = varchar(100)
  }

  column "title" {
    null = false
    type = varchar(100)
  }

  column "price" {
    null = false
    type = float
  }

  column "created_at" {
    null = false
    type = timestamp
  }

  column "updated_at" {
    null = false
    type = timestamp
  }

  column "version" {
    null    = false
    type    = int
    default = 0
  }

  primary_key {
    columns = [column.id]
  }

  index "idx_book_isbn" {
    columns = [column.isbn]
    unique  = true
  }

  foreign_key {
    columns     = [column.author_id]
    ref_columns = [table.author.column.id]
    on_update   = NO_ACTION
    on_delete   = NO_ACTION
  }
}

table "author" {
  schema = schema.public
  column "id" {
    null = false
    type = uuid
  }

  column "name" {
    null = false
    type = varchar(100)
  }

  column "created_at" {
    null = false
    type = timestamp
  }

  column "updated_at" {
    null = false
    type = timestamp
  }

  column "version" {
    null    = false
    type    = int
    default = 0
  }

  primary_key {
    columns = [column.id]
  }

  index "idx_author_name" {
    columns = [column.name]
    unique  = false
  }
}