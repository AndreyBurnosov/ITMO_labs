#!/bin/bash

PARAM_FILE="build.conf"
LOG_FILE="build.log"

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

if [ -f "$LOG_FILE" ]; then
  rm -f "$LOG_FILE"
  touch "$LOG_FILE"
fi

clear

echo "get username & password: https://se.ifmo.ru/passwd/"
read_param "Enter helios username" "${USERNAME:-}" USERNAME
read_param "Enter helios password" "${PASSWORD:-}" PASSWORD
read_param "Enter server" "${SERVER:-helios.cs.ifmo.ru}" SERVER
read_param "Enter port" "${PORT:-2222}" PORT
REMOTE_DIR="~/deploy"

echo "USERNAME=$USERNAME" > "$PARAM_FILE"
echo "PASSWORD=$PASSWORD" >> "$PARAM_FILE"
echo "SERVER=$SERVER" >> "$PARAM_FILE"
echo "PORT=$PORT" >> "$PARAM_FILE"

LOCAL_DEPLOY_DIR="./deploy"

echo ""
echo "Installing Maven and building project..."
(brew install maven || exit 1 ) >> "$LOG_FILE" 2>&1
echo "Maven installed"
(brew install sshpass || exit 1 ) >> "$LOG_FILE" 2>&1
echo "sshpass installed"
(mvn clean package || exit 1 ) >> "$LOG_FILE" 2>&1
echo "Project built"

echo ""
echo "Preparing deployment files..."
rm -rf $LOCAL_DEPLOY_DIR
mkdir -p $LOCAL_DEPLOY_DIR

cp ./client/target/client-1.0-SNAPSHOT.jar $LOCAL_DEPLOY_DIR/client.jar
cp ./server/target/server-1.0-SNAPSHOT.jar $LOCAL_DEPLOY_DIR/server.jar
cp ./common/target/common-1.0-SNAPSHOT.jar $LOCAL_DEPLOY_DIR/common.jar
cp ./sql/create.sql $LOCAL_DEPLOY_DIR/create.sql

touch $LOCAL_DEPLOY_DIR/test.csv
cp server.sh $LOCAL_DEPLOY_DIR/server.sh
cp client.sh $LOCAL_DEPLOY_DIR/client.sh

chmod +x $LOCAL_DEPLOY_DIR/server.sh
chmod +x $LOCAL_DEPLOY_DIR/client.sh

echo ""
echo "Connecting to the server and removing old deployment..."
sshpass -p $PASSWORD ssh -p $PORT $USERNAME@$SERVER "rm -rf $REMOTE_DIR && mkdir -p $REMOTE_DIR" || exit 1

echo ""
echo "Copying new files to the server..."
sshpass -p $PASSWORD scp -P $PORT -r $LOCAL_DEPLOY_DIR/* $USERNAME@$SERVER:$REMOTE_DIR || exit 1

echo ""
echo "Deployment completed successfully!"
echo "To run the project, connect to the server and run the following command:"
echo "cd $REMOTE_DIR && ./server.sh"
echo "cd $REMOTE_DIR && ./client.sh"

echo ""
echo "removing local deployment files..."
rm -rf $LOCAL_DEPLOY_DIR
mvn clean >> "$LOG_FILE" 2>&1
echo "script finished, check $LOG_FILE for details"