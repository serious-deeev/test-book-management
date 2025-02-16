### Preparatory steps

Start DB:
```bash
docker run -d --hostname test_db --name book_management_db -p 5440:5432 -e POSTGRES_USER=tba -e POSTGRES_PASSWORD=password -e POSTGRES_DB=tba -v test-book-management-data:/var/lib/postgresql/data --restart=unless-stopped postgres:14.5
```

Enter to container:
```bash
docker exec -ti book_management_db /bin/bash
```

Create the table:
```bash
# run psql console
psql -U tba

# connect to db
\c tba

# create table
CREATE TABLE books (
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(100),
    description   TEXT,
    author        VARCHAR(100),
    isbn          VARCHAR(20),
    print_year    INT,
    read_already  BOOLEAN,
    image         BYTEA
);
```

### API methods (v1)
| № | HTTP method | URL                                               | DESCRIPTION                                    |
|:--|:------------|:--------------------------------------------------|:-----------------------------------------------|
| 1 | GET         | api/v1/books/{id}                                 | получить информацию о конкретной книге         |
| 2 | GET         | api/v1/books/search?phrase={phrase}&page={number} | найти информацию о книге по фразе с пагинацией |
| 3 | GET         | api/v1/books?page={number}                        | получить список книг с пагинацией              |
| 4 | POST        | api/v1/books                                      | создать книгу                                  |
| 5 | PATCH       | api/v1/books/{id}                                 | отметить книгу прочитанной                     |
| 6 | PUT         | api/v1/books/{id}                                 | обновить книгу                                 |
