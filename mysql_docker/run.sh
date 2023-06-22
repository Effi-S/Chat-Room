# Note: Make sure docker daemon is running and you are in the mysql_docker directory
docker build -t mysql-server .
docker run -d -p 3306:3306 --name mysql-server mysql-server