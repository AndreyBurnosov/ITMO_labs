#!/bin/bash

PARAM_FILE="params.conf"

read_param() {
  local prompt=$1
  local default_value=$2
  local var_name=$3

  if [ -n "$default_value" ]; then
    read -p "$prompt ($default_value): " input
    input=${input:-$default_value}
  else
    read -p "$prompt: " input
  fi

  eval $var_name="'$input'"
}

if [ -f "$PARAM_FILE" ]; then
  source "$PARAM_FILE"
fi

if [ -f "$HOME/.pgpass" ]; then
  PG_PASS_LINE=$(grep '^*:*:*' "$HOME/.pgpass")
  PG_USER_FROM_PGPASS=$(echo $PG_PASS_LINE | cut -d: -f4)
  PG_PASSWORD_FROM_PGPASS=$(echo $PG_PASS_LINE | cut -d: -f5)
fi

read_param "Введите хост базы данных" "${DB_HOST:-pg}" DB_HOST
read_param "Введите порт базы данных" "${DB_PORT:-5432}" DB_PORT
read_param "Введите имя базы данных" "${DB_NAME:-studs}" DB_NAME
read_param "Введите имя пользователя базы данных" "${PG_USER_FROM_PGPASS:-$DB_USER}" DB_USER
read_param "Введите пароль пользователя базы данных" "${PG_PASSWORD_FROM_PGPASS:-$DB_PASSWORD}" DB_PASSWORD

echo 'killing all java processes'
killall java

echo "DB_HOST=$DB_HOST" > "$PARAM_FILE"
# shellcheck disable=SC2129
echo "DB_PORT=$DB_PORT" >> "$PARAM_FILE"
echo "DB_NAME=$DB_NAME" >> "$PARAM_FILE"
echo "DB_USER=$DB_USER" >> "$PARAM_FILE"
echo "DB_PASSWORD=$DB_PASSWORD" >> "$PARAM_FILE"

PGPASSWORD=$DB_PASSWORD psql -h $DB_HOST -p $DB_PORT -U $DB_USER -d $DB_NAME -f create.sql 2>/dev/null

if [ $? -ne 0 ]; then
  echo "Error while executing SQL script."
else
  echo "SQL script executed successfully."
fi


export DB_HOST
export DB_PORT
export DB_NAME
export DB_USER
export DB_PASSWORD

java -jar server.jar