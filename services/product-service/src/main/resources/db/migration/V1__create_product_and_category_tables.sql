
CREATE TABLE IF NOT EXISTS categories(
    id uuid PRIMARY KEY,
    name varchar(100) NOT NULL,
    description text
);

CREATE TABLE IF NOT EXISTS products(
    id uuid PRIMARY KEY,
    name varchar(150) NOT NULL,
    description text,
    price DECIMAL(10, 2) NOT NULL ,
    stock_quantity INTEGER NOT NULL ,
    is_available BOOLEAN default false,
    category_id uuid,
    CONSTRAINT fk_category
            FOREIGN KEY(category_id)
            REFERENCES categories(id)
);

