# Use the latest MySQL image
FROM mysql:latest

# Set the root password
ENV MYSQL_ROOT_PASSWORD=1234

# Copy the init.sql file to the /docker-entrypoint-initdb.d directory
COPY init.sql /docker-entrypoint-initdb.d/

# Expose the MySQL port
EXPOSE 3306